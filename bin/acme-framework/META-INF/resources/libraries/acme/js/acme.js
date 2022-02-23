/*
 * acme.js
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes.  The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

/* Dealing redirections ---------------------------------------------------- */

function getAbsoluteUrl(url) {
	var result;
	var base, currentUrl, anchor, root, path;
	var questionPosition, separator;
	
	base = document.getElementsByTagName("base");
	if (base.length != 1)
		throw new ReferenceError("Your document doesn't have a 'base' element!");
	
	if (/^[\w]+:\/\//.test(url)) {
		result = url;
	} else {
		if (url.charAt(0) == "/") {
			anchor = document.createElement('a');
			anchor.setAttribute("href", base[0].href);
			path = (anchor.pathname == "/" ? "" : (anchor.pathname.endsWith("/") ? anchor.pathname.substring(0, anchor.pathname.length - 1) : anchor.pathname));
			root = anchor.protocol + "//" + anchor.hostname + (anchor.port ? ":" + anchor.port : "") + path;		
			result = root + url;
		} else {
			currentUrl = getCurrentUrl();
			anchor = document.createElement("a");
			anchor.setAttribute("href", currentUrl);
			path = anchor.pathname.substring(0, anchor.pathname.lastIndexOf("/"));
			root = anchor.protocol + "//" + anchor.hostname + (anchor.port ? ":" + anchor.port : "") + path;
			result = root + "/" + url;
		}
	}

	return result;
}

function redirectCurrent() {
	var currentUrl;
	
	currentUrl = getCurrentUrl();
	redirect(currentUrl);
}

function redirectBackwards() {
	var previousUrl;
	
	previousUrl = popReturnUrl();
	
	redirect(previousUrl)
}

function redirect(url, target) {
	var absoluteUrl, tabName;	

	absoluteUrl = getAbsoluteUrl(url);
	if (target)
		tabName = target;	
	else
		tabName = "_self";
	
	window.open(absoluteUrl, tabName);
}

/* Dealing with return URLs ------------------------------------------------ */

const returnStackKey = "return-stack", returnStackSeparator = "\n";

function getCurrentUrl() {
	var result;
	
	result = window.location.href;
	result = getAbsoluteUrl(result);
	
	return result;
}

function clearReturnUrl() {
	sessionStorage.removeItem(returnStackKey);
}

function pushCurrentUrl() {
	var currentUrl;	
	
	currentUrl = getCurrentUrl();
	pushReturnUrl(currentUrl);
}

function pushReturnUrl(url) {
	var absoluteUrl, currentStack;
	
	absoluteUrl = getAbsoluteUrl(url)
	currentStack = sessionStorage.getItem(returnStackKey);
	if (currentStack == null) {
		currentStack = absoluteUrl;		
	} else {
		currentStack = currentStack.concat(returnStackSeparator, absoluteUrl);
	}	

	sessionStorage.setItem(returnStackKey, currentStack);
}

function popReturnUrl() {
	var result;
	var returnStack, position;		
	
	returnStack = sessionStorage.getItem(returnStackKey);
	if (returnStack == null)
		result = getAbsoluteUrl("/");
	else {
		position = returnStack.lastIndexOf(returnStackSeparator);
		if (position == -1) {
			sessionStorage.removeItem(returnStackKey);
			result  = returnStack;
		} else {
			result = returnStack.substring(position + returnStackSeparator.length);
			returnStack = returnStack.substring(0, position);
			sessionStorage.setItem(returnStackKey, returnStack);
		} 
	}
	
	return result;
}

/* Object extensions ------------------------------------------------------- */


if (!String.prototype.format) {
	String.prototype.format = function() {
		var result;
		var args;
		
		args = arguments;
		result = this.replace(/{(\d+)}/g, function(match, number) { 
			return typeof args[number] != "undefined" ? args[number] : match;
		});
		
		return result;
	};
}

