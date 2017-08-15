/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.poshi.runner.elements;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

/**
 * @author Kenji Heigel
 */
public class ForElement extends PoshiElement {

	public static final String ELEMENT_NAME = "for";

	static {
		PoshiElementFactory forElementFactory = new PoshiElementFactory() {

			@Override
			public PoshiElement newPoshiElement(Element element) {
				if (isElementType(ELEMENT_NAME, element)) {
					return new ForElement(element);
				}

				return null;
			}

			@Override
			public PoshiElement newPoshiElement(
				PoshiElement parentPoshiElement, String readableSyntax) {

				if (isElementType(parentPoshiElement, readableSyntax)) {
					return new ForElement(readableSyntax);
				}

				return null;
			}

		};

		PoshiElement.addPoshiElementFactory(forElementFactory);
	}

	public static boolean isElementType(
		PoshiElement parentPoshiElement, String readableSyntax) {

		readableSyntax = readableSyntax.trim();

		if (!isBalancedReadableSyntax(readableSyntax)) {
			return false;
		}

		if (!readableSyntax.startsWith("for (")) {
			return false;
		}

		if (!readableSyntax.endsWith("}")) {
			return false;
		}

		return true;
	}

	@Override
	public String getBlockName() {
		StringBuilder sb = new StringBuilder();

		sb.append("for (");
		sb.append(attributeValue("param"));
		sb.append(" : \"");
		sb.append(attributeValue("list"));
		sb.append("\")");

		return sb.toString();
	}

	@Override
	public void parseReadableSyntax(String readableSyntax) {
		for (String readableBlock : getReadableBlocks(readableSyntax)) {
			if (readableBlock.startsWith("for (")) {
				String parentheticalContent = getParentheticalContent(
					readableBlock);

				String[] parentheticalContentArray = parentheticalContent.split(
					":");

				String param = parentheticalContentArray[0].trim();

				addAttribute("param", param);

				String list = getQuotedContent(
					parentheticalContentArray[1].trim());

				addAttribute("list", list);

				continue;
			}

			add(PoshiElement.newPoshiElement(this, readableBlock));
		}
	}

	@Override
	public String toReadableSyntax() {
		String readableSyntax = super.toReadableSyntax();

		return "\n" + createReadableBlock(readableSyntax);
	}

	protected ForElement(Element element) {
		super(ELEMENT_NAME, element);
	}

	protected ForElement(String readableSyntax) {
		super(ELEMENT_NAME, readableSyntax);
	}

	protected List<String> getReadableBlocks(String readableSyntax) {
		StringBuilder sb = new StringBuilder();

		List<String> readableBlocks = new ArrayList<>();

		for (String line : readableSyntax.split("\n")) {
			if (line.startsWith("for (")) {
				readableBlocks.add(line);

				continue;
			}

			if (!line.startsWith("else {")) {
				String readableBlock = sb.toString();

				readableBlock = readableBlock.trim();

				if (isValidReadableBlock(readableBlock)) {
					readableBlocks.add(readableBlock);

					sb.setLength(0);
				}
			}

			sb.append(line);
			sb.append("\n");
		}

		return readableBlocks;
	}

}