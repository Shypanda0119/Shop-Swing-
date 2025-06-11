package com.sinse.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

//API 자체적으로 지원해주는 진행바는 너무 단순하므로, 우리가 원하는 스타일의 바로 커스텀 해보자 
//Runnable 인터페이스란? run() 추상메서드를 보유한 인터페이스

public class MyBar extends JProgressBar implements Runnable {

	File origin;
	File dest;
	FileInputStream fis;
	FileOutputStream fos;
	int n; // 읽어들인 횟수
	long v;

	public MyBar(File origin, File dest) {
		this.origin = origin;
		this.dest = dest;
		System.out.println("새롭게 생성될 파일의 디렉토리는 " + dest.getParent());
		System.out.println("새롭게 생성될 파일명은 " + dest.getName());
		try {
			fis = new FileInputStream(origin); // 원본 파일을 대상으로 스트림 생성
			fos = new FileOutputStream(dest); // 파일이 복사될 대상에 대한 출력 스트림 생성
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(680, 50));
		setBorder(new TitledBorder("hi"));
		setStringPainted(true); // 진행바 중앙에 텍스트 띄우기
		setForeground(Color.blue);
		setFont(new Font("Verdana", Font.BOLD, 17));
		this.setValue(30);
	}

	// 진행율을 표시하는 메서드
	public void showRate(int n) {
		// 읽어들인량 * 100 / 용량
		v += n;
		this.setValue((int) (v * 100 / origin.length()));
	}

	// 한 바이트씩 읽어들이지 말고, 버퍼를 만들어 한꺼번에 읽어 들이기
	public void copyFast() {

		byte[] buff = new byte[1024];
		int read = 0;
		// 버퍼가 1024의 용량을 갖더라도, 컴퓨터 상황(네트워크, os 상황, 디스크 등등)
		// 이유로 인해 반드시 1024개를 다 모으지 않을 경우도 있으므로..
			try {
				while (true) {
				read = fis.read(buff);
				if (read == -1) break;
				showRate(read);
				fos.write(buff, 0, read);
				Thread.sleep(1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		release();
	}

	// 파일에 생성된 스트림을 통해 읽고, 내뱉자
	public void run() {
		copyFast();
		release();
	}

	public void release() {
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void copySlow() {
		int data = -1;
		try {
			while (true) {
				data = fis.read();// 1byte
				if (data == -1)
					break;
				n++;

				// 프로그래스바 진행율을 표시 -> 읽어들인량 * 100 / 용량
				showRate(n);
				fos.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
