/* ---------------------------------------------------------------
Ecole Centrale Nantes
JS utilities
Author: InfoMaths team
©Centrale Nantes
------------------------------------------------------------------ */

/* ---------------------------------------------------------------
get first element with the given tag in the up hierarchy
@param currentElement: the starting element
@param tagName: the tag to search for
@return: required element or null
------------------------------------------------------------------ */
function getNextParentTag(currentElement, tagName) {
	while ((currentElement !== null)
		&& ((currentElement.nodeType != Node.ELEMENT_NODE)
			|| (currentElement.tagName !== tagName))) {
		currentElement = currentElement.parentNode;
	}
	return currentElement;
}

/* ---------------------------------------------------------------
get first element with the given tag in the sibling
@param currentElement: the starting element
@param tagName: the tag we search for
@return: required element or null
------------------------------------------------------------------ */
function getNextTag(currentElement, tagName) {
    while ((currentElement !== null) 
    		&& ((currentElement.nodeType != Node.ELEMENT_NODE) 
    			|| (currentElement.tagName !== tagName))) {
        currentElement = currentElement.nextSibling;
    }
    return currentElement;
}


function getPreviousTag(currentElement, tagName) {
    while ((currentElement !== null) 
    		&& ((currentElement.nodeType != Node.ELEMENT_NODE) 
    			|| (currentElement.tagName !== tagName))) {
        currentElement = currentElement.previousSibling;
    }
    return currentElement;
}

/* ---------------------------------------------------------------
delete node children
@param currentElement: the parent element
------------------------------------------------------------------ */
function deleteAll(currentElement) {
	if (currentElement != null) {
		while (currentElement.firstChild !== null) {
			currentElement.removeChild(currentElement.firstChild);
		}
	}
}

/* ---------------------------------------------------------------
parse children buttons to set display property
@param currentElement: the parent element
@param property: the property to set
------------------------------------------------------------------ */
function setButtonsProperty(currentElement, property) {
	if (currentElement != null) {
		var current = getNextTag(currentElement.firstChild, "BUTTON");
    	while (current !== null) {
    		current.style.display = property;
        	current = getNextTag(current.nextSibling, "BUTTON");
    	}
	}
}

/* ---------------------------------------------------------------
show children buttons
@param ref: the parent element
------------------------------------------------------------------ */
function showAll(ref) {
	setButtonsProperty(ref, "inline");
}

/* ---------------------------------------------------------------
hide children buttons
@param ref: the parent element
------------------------------------------------------------------ */
function hideAll(ref) {
	setButtonsProperty(ref, "none");
}

/* ---------------------------------------------------------------
create new hidden element and append it to an element
@param currentElement: the parent element
@param name: the input name
@param value: the input value
@return inputElement
------------------------------------------------------------------ */
function addHidden(currentElement, name, value) {
	var inputElement = document.createElement("INPUT");
	inputElement.setAttribute("type", "hidden");
	inputElement.setAttribute("name", name);
	inputElement.setAttribute("value", value);
	
	currentElement.appendChild(inputElement);
	
	return inputElement;
}

/* ---------------------------------------------------------------
create new input element and append it to an element
@param currentElement: the parent element
@param name: the input name
@param value: the input starting value
@param size: the input size
@return inputElement
------------------------------------------------------------------ */
function addInput(currentElement, name, value, type) {
	var inputElement = document.createElement("INPUT");
	inputElement.setAttribute("type", type);
	inputElement.setAttribute("name", name);
	inputElement.setAttribute("value", value);
	
	currentElement.appendChild(inputElement);
	
	return inputElement;
}

/* ---------------------------------------------------------------
create new select element and append it to an element
@param currentElement: the parent element
@param name: the select name
@param items: a js array with select options. items contains objects {value, text}
@param elementValue: current value
@return selectElement
------------------------------------------------------------------ */
function addSelect(currentElement, name, items, elementValue=-1) {
	var selectElement = document.createElement("SELECT");
	selectElement.setAttribute("name", name);
	for (var i=0; i < items.length; i++) {
		var optionElement = document.createElement("OPTION");
		var valueID = items[i].id;
		var valueText = items[i].name;
		
		optionElement.value = valueID;
		var textElement = document.createTextNode(valueText);
		optionElement.appendChild(textElement);
		
		selectElement.appendChild(optionElement);

		if (elementValue == valueID) {
			optionElement.selected = "selected";
			selectElement.value = elementValue;
		}
	}
	currentElement.appendChild(selectElement);
	
	return selectElement;
}

/* ---------------------------------------------------------------
create new text element and append it to an element
@param currentElement: the parent element
@param value: the text to create
------------------------------------------------------------------ */
function addText(currentElement, value) {
	if (value !== undefined) {
		var textElement = document.createTextNode(value);
		currentElement.appendChild(textElement);
	}
}

/* ---------------------------------------------------------------
create new button element and append it to an element
@param currentElement: the parent element
@param iconName: the icon name
@param scriptName: the script name for onclick
@param isVisible: is button visible?
@return buttonElement
------------------------------------------------------------------ */
function addButton(currentElement, iconName, altName, scriptName, isVisible) {
	var buttonElement = document.createElement("BUTTON");
	if (scriptName !== "") {
		buttonElement.setAttribute("onclick", scriptName);
	}
	if (isVisible) {
		buttonElement.style.display = "inline";
	} else {
		buttonElement.style.display = "none";
	}
	
	if (iconName != "") {
		// Button with an icon
		var iconElement = document.createElement("IMG");
		iconElement.setAttribute("src", iconName);
		iconElement.setAttribute("alt", altName);
		iconElement.setAttribute("height", 20);
		buttonElement.appendChild(iconElement);
	} else if (altName != "") {
		// Text only
		addText(buttonElement, altName);
	} else {
		// Nothin g defined, set default value
		addText(buttonElement, "button");
	}
	
	currentElement.appendChild(buttonElement);
	
	return buttonElement;
}

/* ---------------------------------------------------------------
convert text element in a TD to an input element
@param currentElement: the TD element
@param elementName: the input name
@param elementSize: the input size
@param elementText: the input text value
------------------------------------------------------------------ */
function convertTextToInput(currentElement, elementName, elementSize, elementText="") {
    if ((currentElement !== null) 
    	&& (currentElement.nodeType == Node.ELEMENT_NODE) 
    	&& (currentElement.tagName === "TD")) {
    	// Get current text in the TD
    	var text = elementText;
    	if ((currentElement.firstChild !== null)
    		&& (currentElement.firstChild.nodeType === 3)) {
    			text = currentElement.firstChild.textContent;
    	}
    		
		// Replace text by an input element
	    deleteAll(currentElement);
    	addInput(currentElement, elementName, text, elementSize);
    }
}

/* ---------------------------------------------------------------
convert a text element in a TD to a select element - not set to current text value yet
@param currentElement: the TD element
@param elementName: the select name
@param elementsOption: the select options
@param elementValue: the select current value
------------------------------------------------------------------ */
function convertTextToSelect(currentElement, elementName, elementsOption, elementValue=-1) {
	if ((currentElement !== null) 
    	&& (currentElement.nodeType == Node.ELEMENT_NODE) 
    	&& (currentElement.tagName === "TD")) {
    	// Get current text in the TD
    	var text = "";
    	if ((currentElement.firstChild !== null)
    		&& (currentElement.firstChild.nodeType === 3)) {
    			text = currentElement.firstChild.textContent;
    	}
    	
		// Replace text by an select element
    	deleteAll(currentElement);
    	var theSelect = addSelect(currentElement, elementName, elementsOption, elementValue);
        return theSelect;
    }
}

/* ---------------------------------------------------------------
convert an element in a TD to a text element
@param currentElement: the TD element
------------------------------------------------------------------ */
function convertElementToText(currentElement) {
    if ((currentElement !== null) 
    	&& (currentElement.nodeType == Node.ELEMENT_NODE) 
    	&& (currentElement.tagName === "TD")) {
		
		var currentSon = currentElement.firstChild;
		while ((currentSon !== null) 
				&& (currentSon.nodeType != Node.ELEMENT_NODE)
				&& (currentSon.tagName !== "SELECT") && (currentSon.tagName !== "INPUT")) {
			currentSon = currentSon.nextSibling;
		}
		
    	if (currentSon !== null) {
			// Get value according to tagName - SELECT, INPUT, ..
    		var value = "";
    		if (currentSon.tagName === "SELECT") {
    			value = currentSon.options[currentSon.selectedIndex].textContent;
    		} else {
    			value = currentSon.value;
    		}
    		
    		// Replace input element by a text element
    		deleteAll(currentElement);
    		addText(currentElement, value);
 	  	}
 	}
}

/* ---------------------------------------------------------------
convert an element in a TD to a predefined text element
@param currentElement: the TD element
@param value: the text to remplace with
------------------------------------------------------------------ */
function convertToText(currentElement, value) {
	if ((currentElement !== null) 
    	&& (currentElement.nodeType == Node.ELEMENT_NODE) 
    	&& (currentElement.tagName === "TD")) {
    	// There is a TD element
    	deleteAll(currentElement);
     	addText(currentElement, value);
    }
    
    return currentElement;
}

/* ---------------------------------------------------------------
convert editable elements in a TR to text elements
@param teElement: the TR element
------------------------------------------------------------------ */
function convertEditableToText(teElement) {
    var currentElement = teElement.firstChild;

	while (currentElement != null) {
		// IF TD node, convert it
		if ((currentElement.nodeType == Node.ELEMENT_NODE) 
    		&& (currentElement.tagName === "TD")) {
    		convertElementToText(currentElement);
    	}
		currentElement = currentElement.nextSibling;
	}
}

/* ---------------------------------------------------------------
get current values in a TR line, and translate them to a json object
@param refTR: the tr element
@return a json object with values
------------------------------------------------------------------ */
function getCurrentValues(refTR) {
	var jsonString = "{";
	var isFirst = true;
	if (refTR != null) {
		var current = getNextTag(refTR.firstChild, "TD");
    	while (current !== null) {
    		var theInput = getNextTag(current.firstChild, "INPUT");
    		if (theInput !== null) {
    			if (! isFirst) jsonString += ",";
    			isFirst = false;
    			
    			jsonString += '"' + theInput.name + '": "' + theInput.value + '"';
    		} else {
				theInput = getNextTag(current.firstChild, "SELECT");
				if (theInput !== null) {
	    			if (! isFirst) jsonString += ",";
    				isFirst = false;

    				jsonString += '"' + theInput.name + '": "' + theInput.value + '"';
//    				jsonString += ',"' + theInput.name + 'Name": "' + theInput.options[theInput.value].text + '"';
				}
    		}
    		
        	current = getNextTag(current.nextSibling, "TD");
    	}
	}
	jsonString = jsonString+"}";
	var returned = JSON.parse(jsonString);
	
	return returned;
}
