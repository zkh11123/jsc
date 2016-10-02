package com.mgc.common.utils;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;

public class HtmlUtils {
	public static String html(Element element) {
		if (element == null) {
			return null;
		}
		return element.html();
	}

	public static String html(List<Element> elements) {
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		return html((Element) elements.get(0));
	}

	public static String attr(List<Element> elements, String attributeKey) {
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		return attr((Element) elements.get(0), attributeKey);
	}

	public static String attr(Element element, String attributeKey) {
		if (element == null) {
			return null;
		}
		return element.attr(attributeKey);
	}

	public static String val(List<Element> elements) {
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		return val((Element) elements.get(0));
	}

	public static String val(Element element) {
		if (element == null) {
			return null;
		}
		return element.val();
	}

	public static String text(List<Element> elements) {
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		return text((Element) elements.get(0));
	}

	public static String text(List<Element> elements, int index) {
		if (CollectionUtils.isEmpty(elements)) {
			return null;
		}
		return text((Element) elements.get(index));
	}

	public static String text(Element element) {
		if (element == null) {
			return null;
		}
		return StringUtils.isBlank(element.ownText()) ? null : element
				.ownText();
	}
}