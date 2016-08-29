/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.focus3d.agent.common.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CaptchasController.java
 *
 * @author geliang
 */
@Controller
@RequestMapping("/captchas")
public class CaptchasController extends CommonController {
	private static int WIDTH = 100;
	private static int HEIGHT = 40;
	private static int LENGTH = 4;
	private static int numLine = 50;
	// 定义验证码的字符表
	private final static String[] VALIDATE_CHAR_LIB = { "A", "B", "C", "D",
			"E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "2", "3", "4", "5", "6", "7", "8",
			"9" };
	// 定义认证码的字体表
	public static String[] fontTypes = { "arial", "Times New Roman" };
	// 定义认证码的字体风格表
	public static int[] fontStyles = { Font.PLAIN, Font.ITALIC, Font.BOLD,
			Font.BOLD, Font.BOLD, Font.BOLD, Font.ITALIC, Font.BOLD,
			Font.PLAIN, Font.ITALIC, Font.PLAIN, Font.BOLD };

	@RequestMapping(value = "/{id}.jpg", method = RequestMethod.GET)
	public void show(@PathVariable String id, HttpServletResponse response, HttpServletRequest req)
			throws IOException {
		// 设置浏览器不要缓存此图片
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 创建内存图象
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		// 获得其图形上下文
		Graphics g = image.getGraphics();
		// 创建一个随机数生成器
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(random, 200, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 根据当前图片大小，随机生成每个数字位置
		int[][] posDigital = new int[LENGTH][2];
		posDigital = drawRands(g, random);
		// 取随机产生的认证码
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < LENGTH; i++) {
			String rand = VALIDATE_CHAR_LIB[random
					.nextInt(VALIDATE_CHAR_LIB.length)];
			s.append(rand);
			// 设定字体
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)],
					fontStyles[random.nextInt(fontStyles.length)], 28));
			// 将认证码显示到图象中
			Color c1 = getRandColor(random, 20, 130);
			g.setXORMode(c1);
			g.drawString(rand, posDigital[i][0], posDigital[i][1]);
		}
		// 生成图片上的干扰线
		drawBackground(g, random);
		// 结束图像的绘制过程，完成图像
		g.dispose();
		// 将当前验证码存入到Session
		req.getSession().setAttribute("captcha", s.toString());
		// 将图像输出到客户端
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	/**
	 * 根据当前图片大小，随机生成每个数字位置
	 *
	 * @param g
	 * @param random
	 * @return
	 */
	private int[][] drawRands(Graphics g, Random random) {
		int[][] posDigital = new int[LENGTH][2];
		for (int i = 0; i < LENGTH; i++) {
			if ((i % 2) == 1) {
				posDigital[i][0] = posDigital[i - 1][0] + 22 - 2;
			} else {
				if (i == 0) {
					posDigital[i][0] = i * WIDTH / LENGTH
							+ Math.abs(random.nextInt(4));
				} else {
					posDigital[i][0] = posDigital[i - 1][0] + 22;
				}
			}
			posDigital[i][1] = 25 + Math.abs(random.nextInt(HEIGHT - 30));
		}
		return posDigital;
	}

	/**
	 * 给定范围获得随机颜色
	 *
	 * @param random
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(Random random, int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 随机产生干扰线，使图象中的认证码不易被其它程序探测到
	 *
	 * @param g
	 * @param random
	 */
	private void drawBackground(Graphics g, Random random) {
		for (int i = 0; i < numLine; i++) {
			Color c1 = getRandColor(random, 160, 200);
			g.setXORMode(c1);
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int xl = random.nextInt(WIDTH / 3);
			int yl = random.nextInt(HEIGHT / 3);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 画中间的横线
		int totalLineNum = 3 + random.nextInt(2);
		for (int i = 0; i < totalLineNum; i++) {
			Color c1 = new Color(0, 0, 0);
			g.setXORMode(c1);
			int x = i * (WIDTH / totalLineNum) + random.nextInt(5);
			int y = 15 + random.nextInt(20);
			int xl = x + (WIDTH / totalLineNum) + random.nextInt(5);
			int yl = 15 + random.nextInt(20);
			g.drawLine(x, y, xl, yl);
		}
	}
}
