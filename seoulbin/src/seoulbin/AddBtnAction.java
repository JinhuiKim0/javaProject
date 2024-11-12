package seoulbin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

class AddBtnAction extends JFrame{
	public AddBtnAction() {
		// 새창의 제목 설정
		super("추가");
		
		// 새 창의 기본 설정
		setSize(900,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫으면 메모리에서 제거
		setLocationRelativeTo(null); // 화면 중앙에 위치
		
		// 패널 및 레이아웃 설정
		JPanel panel=new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10); // 여백 설정

		
		// 주소 입력 필드 추가
        JLabel addressLabel=new JLabel("주소:");
        JTextField addressField = new JTextField(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(addressLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(addressField, gbc);


        // 체크박스 2개 추가
        JCheckBox checkBox1 = new JCheckBox("재활용");
        JCheckBox checkBox2 = new JCheckBox("일반");
        
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(new JLabel("쓰레기통 종류: "));
        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(checkBoxPanel, gbc);

        // 이미지 표시 공간 추가
        JLabel imageLabel = new JLabel("이미지가 여기에 표시됩니다.", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(600,400));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(imageLabel, gbc);

        // 사진 선택 버튼 추가
        JButton selectImageButton = new JButton("사진 선택");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 파일 선택 대화 상자 열기
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("사진 선택");
                fileChooser.setFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "png", "gif"));

                // 파일을 선택하고 이미지 로드
                int result = fileChooser.showOpenDialog(AddBtnAction.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    
                 // 이미지 크기를 레이블에 맞게 조정
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(imageLabel.getWidth(),imageLabel.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon changeIcon=new ImageIcon(scaledImage);
                    
                    imageLabel.setIcon(changeIcon);
                    imageLabel.setText(""); // 기본 텍스트 제거
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NONE;
        gbc.fill = GridBagConstraints.HORIZONTAL; // 버튼을 가로로 확장
        panel.add(selectImageButton, gbc);

        // 확인 버튼 추가
        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 주소, 체크박스 상태, 선택된 이미지 정보 가져오기
                String address = addressField.getText();
                boolean isCheckBox1Selected = checkBox1.isSelected();
                boolean isCheckBox2Selected = checkBox2.isSelected();
                Icon imageIcon = imageLabel.getIcon(); // 이미지가 선택되었는지 확인

                if(address.isEmpty()) {
                	JOptionPane.showMessageDialog(AddBtnAction.this, "주소를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                	addressField.requestFocus(); // 주소 입력 필드로 포커스 이동
                	return ;// 경고 후 처리 종료
                }
                if(!isCheckBox1Selected&& !isCheckBox2Selected) {
                	JOptionPane.showMessageDialog(AddBtnAction.this, "옵션을 체크해주십시오.", "경고", JOptionPane.WARNING_MESSAGE);
                	checkBox1.requestFocus(); // 체크박스 중 첫 번째에 포커스 이동
                	return ;// 경고 후 처리 종료
                }
                if (imageIcon == null) {
                    JOptionPane.showMessageDialog(AddBtnAction.this, "이미지를 선택해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                    selectImageButton.requestFocus(); // 이미지 선택 버튼에 포커스 이동
                    return;
                }
                //String imagePath = ((ImageIcon) imageIcon).getDescription();
                // 데베에 전달: 주소, 체크박스 체크여부 2개, 이미지 경로 
                //saveAddBin(address,isCheckBox1Selected, isCheckBox2Selected, imagePath);
                System.out.println("데이터 베이스에 전달"); // 확인용
                
                String message = "주소: " + address + "\n" +
                                 "옵션 1: " + (isCheckBox1Selected ? "선택됨" : "선택 안됨") + "\n" +
                                 "옵션 2: " + (isCheckBox2Selected ? "선택됨" : "선택 안됨" + "\n" +
                                 "사진 : 첨부됨.");
                

                // 메시지 표시
                JOptionPane.showMessageDialog(AddBtnAction.this, message, "입력된 정보", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // 버튼 크기 키우기
        confirmButton.setPreferredSize(new Dimension(115, 35)); // 확인 버튼 크기
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(confirmButton, gbc);

        // 패널을 프레임에 추가
        add(panel);
    }
}