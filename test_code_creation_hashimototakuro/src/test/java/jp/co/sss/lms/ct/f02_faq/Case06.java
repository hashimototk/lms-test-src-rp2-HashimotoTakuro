package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// ログイン画面を開く
		goTo("http://localhost:8080/lms/");

		// タイトルの検証
		assertEquals("ログイン | LMS", webDriver.getTitle());

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// ログインIDを入力する
		WebElement loginId = webDriver.findElement(By.name("loginId"));
		loginId.clear();
		loginId.sendKeys("StudentAA01");

		// パスワードを入力する
		WebElement password = webDriver.findElement(By.name("password"));
		password.clear();
		password.sendKeys("StudentAA011");

		// ログインボタンを押下
		WebElement loginButton = webDriver.findElement(By.className("btn-primary"));
		loginButton.click();

		// タイトルの検証
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// 上部メニューから「機能」リンクを押下
		WebElement functionLink = webDriver.findElement(By.linkText("機能"));
		functionLink.click();

		// ドロップダウンメニュー内の「ヘルプ」リンクを押下
		WebElement helpLink = webDriver.findElement(By.linkText("ヘルプ"));
		helpLink.click();

		// タイトルの検証
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 元タブを保存
		String helpTab = webDriver.getWindowHandle();

		// 「よくある質問」リンクを押下
		WebElement faqLink = webDriver.findElement(By.linkText("よくある質問"));
		faqLink.click();

		// 新しいタブに切り替える
		for (String faqTab : webDriver.getWindowHandles()) {
			if (!faqTab.equals(helpTab)) {
				webDriver.switchTo().window(faqTab);
				break;
			}
		}

		// タイトルの検証
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// 「【研修関係】」リンクを押下
		WebElement categoryLink = webDriver.findElement(By.linkText("【研修関係】"));
		categoryLink.click();

		// 検索結果の検証
		WebElement categoryResult = webDriver.findElement(By.id("question-h[${status.index}]"));
		assertEquals("Q.キャンセル料・途中退校について", categoryResult.getText());

		// 検索結果が見えるようスクロール
		scrollTo("200");

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// 「Q.キャンセル料・途中退校について」を押下
		WebElement question = webDriver.findElement(By.className("mb10"));
		question.click();

		// 回答が表示されているかの検証
		WebElement answer = webDriver.findElement(By.id("answer-h[${status.index}]"));
		String answerResult = answer.getText();
		assertTrue(answerResult.contains("受講者の退職"));

		// 回答の表示が見えるようスクロール
		scrollTo("200");

		// 開いたページのキャプチャを保存する
		getEvidence(new Object() {
		});
	}

}
