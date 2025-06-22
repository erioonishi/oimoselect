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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
//java.awt.* はレイアウトやデザイン関連のクラスをインポート
//javax.swing.* はSwingのGUIコンポーネント(ボタン、ラベル、フレームなど)を扱うためのクラスをインポート


//JavaのSwingではGUIの描画処理をイベントディスパッチスレッド(EDT)上で実行するのが推奨
//SwingUtilities.invokeLater(Potato::createAndShowGUI);で、createAndShowGUI()メソッドをEDTで実行
public class Potato {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Potato::createAndShowGUI);
    }
    
    // GUI を作成するメソッド
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("おいもスイーツ診断");//JFrame(ウィンドウ)を作成し、タイトルを「おいもスイーツ診断」に設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じたらアプリ終了
        frame.setSize(500, 500);//ウィンドウサイズを500x500 に設定
        frame.setLayout(new GridBagLayout());//GridBagLayoutをレイアウトマネージャに設定(細かいレイアウト調整が可能)

        // 最初の質問
        JLabel question1 = createStyledLabel("素材そのまま？");//"素材そのまま？" という質問ラベルを作成
        GridBagConstraints gbc = new GridBagConstraints();//GridBagConstraints を設定
        gbc.gridx = 0;//gbc.gridx = 0; gbc.gridy = 0; で左上に配置
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(question1, gbc);

        // YES/NO ボタン
        JButton yesButton = createColoredButton("YES", new Color(255, 204, 102));//YESボタン(オレンジ色)とNOボタン赤色を作成
        JButton noButton = createColoredButton("NO", new Color(204, 102, 102));

        //ボタンを画面に追加
        gbc.gridy = 1;
        frame.add(yesButton, gbc);//YESボタンを1行目に配置

        gbc.gridy = 2;
        frame.add(noButton, gbc);//NO ボタンを2行目に配置

        // ボタンの動作
        yesButton.addActionListener(e -> chooseTexture(frame));//YESボタンが押されたらchooseTexture(frame)を実行(次の質問へ)
        noButton.addActionListener(e -> chooseSweetType(frame));//NOボタンが押されたらchooseSweetType(frame)を実行

        frame.setVisible(true);
    }
    
    //ラベルを作成するメソッド
    private static JLabel createStyledLabel(String text) {//private static→このクラス内でのみ使えるメソッドでオブジェクトを作らずに使える
    	//JLabel を戻り値として返す
        JLabel label = new JLabel(text, SwingConstants.CENTER);//JLabelを作成し、中央揃えにする。text→引数で受け取ったテキストをセット
        //SwingConstants.CENTER→ラベルのテキストを中央揃えにする
        label.setFont(new Font("SansSerif", Font.BOLD, 18));//指定したテキストでJLabel(中央揃え、太字フォント)を作成
        return label;//作成したlabelを呼び出し元に返す
    }
    
    //ボタンを作成するメソッド(指定した色のボタンを作成)
    private static JButton createColoredButton(String text, Color color) {//private static→このクラス内でのみ使えるメソッドでオブジェクトを作らずに使える
    	//JButton を戻り値として返す
        JButton button = new JButton(text);//ボタン(JButton)を作成し、ボタンのテキストを設定。text→引数で受け取ったテキストをボタンのラベルとして表示
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(200, 50));//ボタンのサイズを指定。幅200px、高さ50pxのボタンを作成
        button.setBackground(color);//(引数で受け取った色)を適用
        button.setForeground(Color.WHITE); //文字色を白に統一
        button.setOpaque(true);//ボタンの背景を不透明にする（true)(false だと背景が透けることがある)
        button.setBorderPainted(false);//ボタンの枠線を非表示にする(デザインをスッキリさせるため）
        return button;//作成したbuttonを呼び出し元に返す
    }
    
    //「もっちり or ほくほく？」の選択画面(画面をリセットして「もっちり or ほくほく？」の質問を表示)
    private static void chooseTexture(JFrame frame) {//private static→このクラス内でのみ使えてオブジェクトを作らずに呼び出せる
    	//JFrame frame → 現在のウィンドウ(JFrame)を受け取る(画面を更新するため)
        frame.getContentPane().removeAll();//removeAll()→既存のコンポーネント(ボタンやラベル)をすべて削除
        frame.setLayout(new GridBagLayout());//setLayout(new GridBagLayout())→グリッド状に要素を配置するレイアウトを適用

        JLabel question = createStyledLabel("もっちり or ほくほく？");//質問ラベルを作成（「もっちり or ほくほく？」）
        //createStyledLabel()を使い、中央揃え＆太字のラベルを作成

        JButton mochiButton = createColoredButton("もっちり", new Color(255, 204, 102));//mochiButton→「もっちり」を選ぶボタン(黄色系)
        JButton hokuButton = createColoredButton("ほくほく", new Color(204, 102, 102));//hokuButton→「ほくほく」を選ぶボタン(赤系)

        GridBagConstraints gbc = new GridBagConstraints();//GridBagConstraints(配置のルール)を作成
        gbc.gridx = 0;//列(横方向)は0番目
        gbc.gridy = 0;//行(縦方向)は0番目(一番上に配置)
        gbc.insets = new Insets(10, 10, 10, 10);//上下左右に10pxの余白
        frame.add(question, gbc);//ラベルをウィンドウに追加

        gbc.gridy = 1; //「もっちり」ボタンを追加(1行目に配置)
        frame.add(mochiButton, gbc);

        gbc.gridy = 2;//「ほくほく」ボタンを追加(2行目に配置)
        frame.add(hokuButton, gbc);

        
        //ボタンのアクション
        hokuButton.addActionListener(e -> showResult(frame, "オススメは焼き芋！", "/images/yakiimo.png"));//ほくほくを選ぶと「焼き芋」へ
        //addActionListener(e -> showResult(...))→ボタンが押されたら showResult メソッドを実行
        //showResult(frame, "オススメは焼き芋！", "/images/yakiimo.png")→「オススメは焼き芋！」の結果画面を表示
        mochiButton.addActionListener(e -> showResult(frame, "オススメは干し芋！", "/images/hosiimo.png"));//もっちりを選ぶと「干し芋」へ

        frame.revalidate();//レイアウトを再計算(更新)
        frame.repaint();//再描画して変更を適用
    }

    //「和菓子 or 洋菓子？」の選択画面
    private static void chooseSweetType(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());

        JLabel question = createStyledLabel("和菓子派 or 洋菓子派？");

        JButton wagashiButton = createColoredButton("和菓子", new Color(255, 204, 102));
        JButton yougashiButton = createColoredButton("洋菓子", new Color(204, 102, 102));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        frame.add(question, gbc);

        gbc.gridy = 1;
        frame.add(wagashiButton, gbc);

        gbc.gridy = 2;
        frame.add(yougashiButton, gbc);

        //ボタンのアクション
        wagashiButton.addActionListener(e -> chooseWagashi(frame));//和菓子を選ぶとchooseWagashi(frame)を実行
        yougashiButton.addActionListener(e -> chooseYougashi(frame));//洋菓子を選ぶとchooseYougashi(frame)を実行

        frame.revalidate();
        frame.repaint();
    }
    //「和菓子」の詳細選択画面
    private static void chooseWagashi(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());

        JLabel question = createStyledLabel("しっとり or もっちり or カリカリ？");

        JButton shittoriButton = createColoredButton("しっとり", new Color(255, 204, 102));
        JButton mocchiButton = createColoredButton("もっちり", new Color(204, 102, 102));
        JButton kariButton = createColoredButton("カリカリ", new Color(102, 102, 204));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        //質問ラベル
        gbc.gridy = 0;
        frame.add(question, gbc);

        //ボタンを縦に配置
        gbc.gridy = 1;
        frame.add(shittoriButton, gbc);

        gbc.gridy = 2;
        frame.add(mocchiButton, gbc);

        gbc.gridy = 3;
        frame.add(kariButton, gbc);

        shittoriButton.addActionListener(e -> showResult(frame, "オススメはおいもようかん！", "/images/imoyoukan.png"));
        mocchiButton.addActionListener(e -> showResult(frame, "オススメはおにまんじゅう！", "/images/onimanjyu.png"));
        kariButton.addActionListener(e -> showResult(frame, "オススメは大学芋！", "/images/daigakuimo.png"));

        frame.revalidate();
        frame.repaint();
    }
    
    //「洋菓子」の詳細選択画面
    private static void chooseYougashi(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());

        JLabel question = createStyledLabel("しっとり or とろり or ふんわり？");
        JButton shittoriButton = createColoredButton("しっとり", new Color(255, 204, 102));
        JButton tororiButton = createColoredButton("とろり", new Color(204, 102, 102));
        JButton funwariButton = createColoredButton("ふんわり", new Color(102, 102, 204));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        //質問ラベル
        gbc.gridy = 0;
        frame.add(question, gbc);

        //ボタンを縦に配置
        gbc.gridy = 1;
        frame.add(shittoriButton, gbc);

        gbc.gridy = 2;
        frame.add(tororiButton, gbc);

        gbc.gridy = 3;
        frame.add(funwariButton, gbc);
        
        shittoriButton.addActionListener(e -> showResult(frame, "オススメはスイートポテト！", "/images/sweetpotato.png"));
        tororiButton.addActionListener(e -> showResult(frame, "オススメはおいもプリン！", "/images/oimopurin.png"));
        funwariButton.addActionListener(e -> showResult(frame, "オススメはおいもパウンドケーキ！", "/images/paundcake.png"));

        frame.revalidate();
        frame.repaint();
    }
    
    //診断結果を表示するメソッド
    //showResultは診断結果を表示するためのメソッドでウィンドウframeに結果のメッセージと画像を表示
    //(private static→このクラス内だけで使える、オブジェクトを作らずに呼び出せる）
    private static void showResult(JFrame frame, String result, String imagePath) {
        frame.getContentPane().removeAll();//frameのコンテンツをすべて削除して前の質問画面をクリア
        //(前のボタンやテキストを消して、結果画面を表示する準備）
        frame.setLayout(new GridBagLayout());//GridBagLayoutを適用しコンポーネント(ラベル・画像・ボタン)を柔軟に配置できるようにする
        //(ボタンや画像を 中央揃え で配置するため）

        JLabel resultLabel = createStyledLabel(result);//result(診断結果のテキスト)をJLabelにセットし、createStyledLabelを使ってフォントなどを装飾
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 23));//結果メッセージのフォントを太字でサイズ23px 

        ImageIcon icon = new ImageIcon(Potato.class.getResource(imagePath));//imagePath(画像のパス)から診断結果の画像を読み込む
        JLabel imageLabel = new JLabel(icon);//imagePath(画像のパス)から診断結果の画像を読み込む

        JButton restartButton = createColoredButton("もう一度診断する", new Color(153, 102, 51));//「もう一度診断する」ボタンを作成し、背景色を茶色(153, 102, 51)にする。
        //(このボタンを押すと、最初の画面に戻れる)
        restartButton.setForeground(Color.WHITE);//ボタンの文字色を白
        restartButton.addActionListener(e -> {//「もう一度診断する」ボタンが押されたら
            frame.dispose();//今のウィンドウを閉じる
            createAndShowGUI();//最初の診断画面を開く
        });

        GridBagConstraints gbc = new GridBagConstraints();//GridBagLayoutでレイアウトの細かい設定をするためのオブジェクト作成。
        //(このgbcを使って、ラベルや画像を中央に配置）
        gbc.gridx = 0;//横の位置（0番目 = 左端）
        gbc.gridy = 0;//縦の位置（0番目 = 一番上）
        gbc.insets = new Insets(10, 10, 10, 10);//余白を10pxずつ確保(上下左右）
        frame.add(resultLabel, gbc);//診断結果のテキストを画面に追加
        //診断結果の画像をメッセージの下に配置
        gbc.gridy = 1;//1つ下の位置にセット
        frame.add(imageLabel, gbc);//画像を画面に追加

        gbc.gridy = 2;//さらに1つ下の位置にセット
        frame.add(restartButton, gbc);//ボタンを画面に追加

        frame.revalidate();//レイアウトを再計算(新しいコンテンツを反映）
        frame.repaint();//画面を再描画(新しい要素をしっかり表示）
    }
}
