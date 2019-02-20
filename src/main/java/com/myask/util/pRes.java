package com.myask.util;

import javax.servlet.http.HttpSession;

public abstract class pRes {
	public static void setThreadAttr(HttpSession session, String attr) throws Exception{
		session.setAttribute(attr, true);
		new Thread(() -> {
			session.setAttribute(attr, true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// ignore
				e.printStackTrace();
			}
			session.removeAttribute(attr);
		}).start();
	}
}
