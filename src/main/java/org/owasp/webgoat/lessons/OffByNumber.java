package org.owasp.webgoat.lessons;

import java.util.ArrayList;
import java.util.List;

import org.apache.ecs.Element;
import org.apache.ecs.ElementContainer;
import org.apache.ecs.StringElement;
import org.apache.ecs.html.IMG;
import org.apache.ecs.html.Input;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.xhtml.br;
import org.owasp.webgoat.session.ECSFactory;
import org.owasp.webgoat.session.ParameterNotFoundException;
import org.owasp.webgoat.session.ValidationException;
import org.owasp.webgoat.session.WebSession;

/***************************************************************************************************
 * 
 * 
 * This file is part of WebGoat, an Open Web Application Security Project
 * utility. For details, please see http://www.owasp.org/
 * 
 * Copyright (c) 2002 - 20014 Bruce Mayhew
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 * 
 * Getting Source ==============
 * 
 * Source for this application is maintained at
 * https://github.com/WebGoat/WebGoat, a repository for free software projects.
 * 
 * For details, please see http://webgoat.github.io
 * 
 * @author weiss <a href="http://code.google.com/p/webgoat">WebGoat</a>
 * @created Mar 23, 2015
 */
public class OffByNumber extends LessonAdapter {
	private final static String[] price_plans = { "12000 ", "11000", "10000" };

	private final static String BUY_NUMBER = "	buy_no";

	private final static String WATCH = "watch";

	private final static IMG LOGO = new IMG("images/logos/seleucus.png")
			.setAlt("Seleucus Ltd").setBorder(0).setHspace(0).setVspace(0);

	/**
	 * <p>
	 * The main method for creating content, implemented from the the
	 * LessonAdapter class.
	 * </p>
	 * 
	 * <p>
	 * This particular "Off-by-Number" lesson belonging in the category of
	 * "Buffer Overflows" carries three steps.
	 * </p>
	 * 
	 * @param s
	 *            WebSession
	 * @return Description of the Return Value
	 */
	protected Element createContent(WebSession s) {
		ElementContainer ec = new ElementContainer();

		try {
			if (s.getParser().getRawParameter(BUY_NUMBER, "").isEmpty()) {
				ec.addElement(createMainContent(s));
			} else {
				ec.addElement(makeResult(s));
			}

		} catch (Exception e) {
			s.setMessage("Error generating " + this.getClass().getName());
			e.printStackTrace();
		}

		return (ec);
	}

	/**
	 * <p>
	 * Returns the Buffer Overflow category for this lesson.
	 * </p>
	 * 
	 * @return The category value
	 */
	protected Category getDefaultCategory() {
		return Category.BUFFER_OVERFLOW;
	}

	/**
	 * <p>
	 * Returns the hints as a List of Strings for this lesson.
	 * </p>
	 * 
	 * @return The hints values
	 */
	public List<String> getHints(WebSession s) {
		List<String> hints = new ArrayList<String>();
		hints.add("可以尝试把购买的数量设置的大一点？");
		hints.add("任性一会，买2000000台watch吧!做会土豪看看 :)");
		return hints;
	}

	/**
	 * <p>
	 * Get the default ranking within the "Buffer Overflow" category.
	 * </p>
	 * 
	 * <p>
	 * Currently ranked to be the first lesson in this category.
	 * </p>
	 * 
	 * @return The value of 5 as an Integer Object
	 */
	protected Integer getDefaultRanking() {
		return new Integer(5);
	}

	/**
	 * <p>
	 * Gets the title attribute for this lesson.
	 * </p>
	 * 
	 * @return "Off-by-Number Overflows"
	 */
	public String getTitle() {
		return ("Off-by-Number Overflows");
	}

	/**
	 * weiss in china 
	 */
	public Element getCredits() {
		return super.getCustomCredits("Created by weiss ", LOGO);
	}

	/**
	 * <p>
	 * Method for create the content and returning it as an Element.
	 * </p>
	 * 
	 * @param s
	 * @return The Element that is the first step.
	 */
	private Element createMainContent(WebSession s) {
		ElementContainer ec = new ElementContainer();
		String param = "";

		// Header
		ec.addElement(new StringElement(
				"Apple公司发布了新的watch产品，这是一个模拟的在线购买页面，你可以在下面选择你要购买的产品和填写购买的数量，完成支付:"));
		ec.addElement(new br());
		ec.addElement(new br());

		ec.addElement(new StringElement("想购买土豪版watch，但是现在拥有的金额不够，试试怎么办呢？."));
		ec.addElement(new br());
		ec.addElement(new br());

		// Table
		Table t = new Table().setCellSpacing(0).setCellPadding(2).setBorder(0)
				.setWidth("90%").setAlign("center");

		if (s.isColor()) {
			t.setBorder(1);
		}

		TR tr = new TR();
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		t.addElement(tr);

		tr = new TR();
		tr.addElement(new TD().addElement("价格: "));
		tr.addElement(new TD().addElement(ECSFactory.makePulldown(WATCH,
				price_plans, price_plans[2], 1)));
		tr.addElement(new TD().addElement("*"));
		t.addElement(tr);
		tr = new TR();
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		t.addElement(tr);

		// Buy Number
		try {
			param = s.getParser().getStrictAlphaParameter(BUY_NUMBER, 25);
		} catch (ParameterNotFoundException e) {
			param = "";
		} catch (ValidationException e) {
			param = "";
		}
		Input input = new Input(Input.TEXT, BUY_NUMBER, param);

		tr = new TR();
		tr.addElement(new TD().addElement("数量: "));
		tr.addElement(new TD().addElement(input));
		tr.addElement(new TD().addElement("*"));
		t.addElement(tr);
		tr = new TR();
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement("&nbsp;"));
		t.addElement(tr);

		// Submit
		tr = new TR();
		tr.addElement(new TD().addElement("&nbsp;"));
		tr.addElement(new TD().addElement(ECSFactory.makeButton("Submit")));
		tr.addElement(new TD().addElement("&nbsp;"));
		t.addElement(tr);

		ec.addElement(t);

		return ec;
	}

	/**
	 * <p>
	 * Method for constructing the attack is success ? returning it as an Element.
	 * </p>
	 * 
	 * @param s
	 * @return The Element that result.
	 */
	private Element makeResult(WebSession s) {
		ElementContainer ec = new ElementContainer();

		ec.addElement(new StringElement("您已经完成了产品的选择和明确了需要购买的数量"));
		ec.addElement(new br());
		ec.addElement(new br());
		ec.addElement(new StringElement("现在您需要支付："));
		ec.addElement(new br());
		ec.addElement(new br());

		int param1 = s.getParser().getIntParameter(BUY_NUMBER, 12000);
		int param2 = s.getParser().getIntParameter(WATCH, 12);
		int count = param1 * param2;
		System.out.println(param1);

		System.out.println(param2);

		ec.addElement(new StringElement("人民币 " + String.valueOf(count)));
		ec.addElement(new br());
		ec.addElement(new br());

		if (param2 < count) {
			s.setMessage("不划算~~再试试！！");
		} else {
			makeSuccess(s);
		}

		ec.addElement(new br());
		ec.addElement(new br());

		return ec;
	}

}
