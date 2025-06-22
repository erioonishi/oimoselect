package oimoselect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

//java.awt.* →レイアウトやデザイン関連のクラスをインポート
//javax.swing.* はSwingのGUIコンポーネント(ボタン、ラベル、フレームなど)を扱うためのクラスをインポート

public class Login { // Loginクラスの定義
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(Login::createAndShowLogin); //GUIの描画処理をイベントディスパッチスレッドで実行
  } 
    //Java Swingではイベントディスパッチスレッド(EDT)がGUIの描画やボタンのクリック処理などを全部1人で担当している
    //SwingUtilities→GUIの更新をEDTで実行するためのもの
    
    	// ログインウインドウ
    public static void createAndShowLogin() { 
        JFrame frame = new JFrame("ログイン画面"); //新しいウィンドウ(JFrame)を作成し、タイトルを「ログイン画面」にする
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ウィンドウを閉じたらプログラム終了
        frame.setSize(500, 500); //ウィンドウのサイズを 500x500 に設定(診断画面と統一)
        frame.setLayout(new GridBagLayout()); //レイアウトをGridBagLayoutに設定(細かい配置を調整できる)
        GridBagConstraints gbc = new GridBagConstraints(); //GridBagLayout用のレイアウト制御オブジェクトを作成
        gbc.insets = new Insets(10, 5, 10, 5); //各コンポーネントの余白を設定(上下10px、左右5px)
        gbc.anchor = GridBagConstraints.CENTER; //コンポーネントを中央寄せに配置
        //JavaのGridBagConstraints(グリッド・コンストレイント)は、コンポーネントの表示領域をグリッド上に配置する場所や
        //配置方法、コンポーネントのサイズなどを指定するオブジェクト→GBC

        // ロゴ画像
        ImageIcon logoIcon = new ImageIcon(Login.class.getResource("/images/oimostartmini.png")); //ロゴ画像を読み込む
        JLabel logoLabel = new JLabel(logoIcon); //画像をJLabelにセット
        gbc.gridx = 0; //0列目に配置
        gbc.gridy = 0; //0行目に配置
        gbc.gridwidth = 2; //画像を横2列分の幅で表示
        frame.add(logoLabel, gbc); //フレームに追加
        //JLabelのインスタンスを、指定されたテキストで作成
       
        // パスワード入力エリア
        JLabel passLabel = new JLabel("パスワード:"); //パスワード入力欄のラベル
        passLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); //フォントを大きく設定(18pt)
        JPasswordField passField = new JPasswordField(10); //パスワード入力フィールド(10文字分の幅)
        passField.setPreferredSize(new Dimension(200, 30)); //入力フィールドのサイズを設定(横200px, 縦30px)

        gbc.gridy = 1; //1行目に配置
        gbc.gridwidth = 1; //1列の幅に戻す
        gbc.gridx = 0; //0列目に配置
        gbc.insets = new Insets(10, 10, 10, 2); //ラベルと入力フィールドの間隔を狭くする
        frame.add(passLabel, gbc); //フレームに追加

        gbc.gridx = 1; //1列目に配置
        gbc.insets = new Insets(10, 2, 10, 10); //入力フィールド側の間隔も調整
        gbc.fill = GridBagConstraints.HORIZONTAL; //入力フィールドを横方向に広げる
        frame.add(passField, gbc); //フレームに追加

        // エラーメッセージ
        JLabel errorLabel = new JLabel("パスワードが違います"); //パスワードが間違っていた時のエラーメッセージ
        errorLabel.setForeground(Color.RED); //エラーメッセージの文字色を赤にする
        errorLabel.setVisible(false); //初期状態では非表示にする
        gbc.gridy = 2; //2行目に配置
        gbc.gridx = 0; //0列目に配置
        gbc.gridwidth = 2; //エラーメッセージは2列分の幅で表示
        frame.add(errorLabel, gbc); //フレームに追加

        // 「進む」ボタン
        JButton loginButton = new JButton("進む"); //ログインボタンを作成
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18)); //フォントを大きくする(18pt)
        loginButton.setBackground(new Color(255, 204, 102)); //ボタンの背景色を設定(おいも色)
        loginButton.setOpaque(true); //ボタンの不透明度を設定
        loginButton.setBorderPainted(false); //ボタンの枠線を非表示にする
        loginButton.setPreferredSize(new Dimension(200, 50)); //ボタンのサイズを設定(200x50)
        
        //クラスJButton→「プッシュ」ボタンの実装
        loginButton.addActionListener(e -> { //ボタンがクリックされたときの処理
            String inputPass = new String(passField.getPassword()); //入力されたパスワードを取得
            if ("oimo".equals(inputPass)) { //正しいパスワードなら…
                frame.dispose(); //ログイン画面を閉じる
                Potato.createAndShowGUI(); //診断画面(Potato.java)を開く
            } else { // 間違っていたら…
                errorLabel.setVisible(true); //エラーメッセージを表示
            }
        });

        gbc.gridy = 3; //3行目に配置
        frame.add(loginButton, gbc); //フレームに追加
        frame.setVisible(true); //ウィンドウを表示
    }
}