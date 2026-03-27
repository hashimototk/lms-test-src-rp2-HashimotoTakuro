package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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
 * 結合テスト レポート機能
 * ケース08
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース08 受講生 レポート修正(週報) 正常系")
public class Case08 {

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
	@DisplayName("テスト03 提出済の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// 初回トリガー
		scrollTo("100");

		for (int i = 0; i < 20; i++) {

			List<WebElement> buttons = webDriver.findElements(
					By.xpath("//tr[.//td[contains(text(),'アルゴリズム')]]//input[@value='詳細']"));

			if (!buttons.isEmpty()) {
				buttons.get(0).click();
				break;
			}

			scrollTo("100");
		}
		// タイトルの検証
		pageLoadTimeout(5);
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		// スクショ
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「確認する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		scrollTo("200");
		WebElement weekReportButton = webDriver.findElement(
				By.cssSelector("input[value='提出済み週報【デモ】を確認する']"));
		weekReportButton.click();
		// タイトルの検証
		pageLoadTimeout(5);
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		// スクショ
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を修正して「提出する」ボタンを押下しセクション詳細画面に遷移")
	void test05() {
		// 報告内容を修正する
		WebElement impression = webDriver.findElement(By.id("content_1"));
		impression.clear();
		impression.sendKeys("週報編集テスト");

		WebElement lookback = webDriver.findElement(By.id("content_2"));
		lookback.clear();
		lookback.sendKeys("一週間の振り返り編集テスト");

		scrollTo("500");
		WebElement submit = webDriver.findElement(By.xpath("//button[text()='提出する']"));
		submit.click();

		// タイトルの検証
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		// スクショ
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 上部メニューの「ようこそ○○さん」リンクからユーザー詳細画面に遷移")
	void test06() {
		// 「ようこそ受講生ＡＡ１さん」リンクを押下する
		webDriver.findElement(By.linkText("ようこそ受講生ＡＡ１さん")).click();
		;
		// タイトルの検証
		pageLoadTimeout(5);
		assertEquals("ユーザー詳細 | LMS", webDriver.getTitle());

		// スクショ
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(7)
	@DisplayName("テスト07 該当レポートの「詳細」ボタンを押下しレポート詳細画面で修正内容が反映される")
	void test07() {
		scrollTo("900");
		webDriver.findElement(By.xpath("//tr[.//td[contains(text(),'週報【デモ】')]]//input[@value='詳細']")).click();
		// スクショ
		getEvidence(new Object() {
		});
	}

}
