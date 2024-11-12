package seoulbin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testMain {
	public static void main(String[] args) {
		JFrame mainFrame=new JFrame("Main Window");
		mainFrame.setSize(400,300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null); // 화면 중앙에 위치
		
		// 버튼 추가
		JButton openButton=new JButton("새 창 열기");
		mainFrame.getContentPane().add(openButton, BorderLayout.CENTER);
		
		openButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//  새 창을 열때 SecondFrame을 띄움.
				AddBtnAction secondFrame=new AddBtnAction();
				secondFrame.setVisible(true);
			}
		});
		
		// 메인창 보이기
		mainFrame.setVisible(true);
		
		// 전역 폰트 설정 (예시)
        FontSet.setGlobalFont(new Font("Malgun Gothic", Font.BOLD, 18));
	}
}