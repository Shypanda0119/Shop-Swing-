package com.sinse.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import com.sinse.shop.AppMain;
import com.sinse.shop.common.config.Config;
import com.sinse.shop.common.config.Page;
import com.sinse.shop.common.util.ImageUtil;
import com.sinse.shop.product.model.Product;
import com.sinse.shop.product.repository.ProductDAO;
import com.sinse.shop.product.view.ProductItem;

public class MainPage extends Page {
	JPanel p_visual; // 메인 비쥬얼 영역(메인 배너 영역)
	JPanel p_content; // 상품이 출력될 영역
	ImageUtil imageUtil = new ImageUtil();
	Image image;
	ProductDAO productDAO = new ProductDAO();
	
	public MainPage(AppMain appMain) {
		super(appMain);
		// 생성
		// 패널을 이름없는 익명 클래스로 재정의하는 코드를 작성.. 장점? 별도의 .java파일을 생성할 필요 없음
		// 내부 클래스이다 보니 외부클래스인 MainPage의 멤버를 공유할 수 있다.
		image = imageUtil.getImage("images/sungalsses.jpg", Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT);
		p_visual = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); // update()에 지워진 배경을 스스로 복구

				// Toolkit은 이미지를 구성하는 바이트 정보에 접근 불가하다..
				// BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 더 다양한 제어가 가능..
				// 우리가 원하는 그림을 그리자.. 즉 패널의 그림을 뺏어 그리자!!
				g.drawImage(image, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual);
			}
		};
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));

		// 스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, 410));

		// 자신의 크기 설정
		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH,
				Config.SHOPMAIN_HEIGHT - (Config.UTIL_HEIGHT + Config.NAVI_HEIGHT)));

		p_visual.setBackground(Color.cyan);
		p_content.setBackground(Color.red);
		
		//최신 상품 생성하기
		createRecentList();
		
		// 조립
		add(p_visual);
		add(p_content);

		setVisible(true);
	}
	
	//최신 상품 패널 원하는 수만큼 p_content에 출력
	public void createRecentList() {
		 List<Product> productList = productDAO.selectRecentList(1);
		for(int i=0; i<productList.size(); i++) {
			Product product = productList.get(i); //리스트에서 상품을 하나씩 꺼내자!
			
			ProductItem productItem = new ProductItem(product);//상품 하나를 표현하는 디자인 카드
		
			p_content.add(productItem);
		}
	}
}
