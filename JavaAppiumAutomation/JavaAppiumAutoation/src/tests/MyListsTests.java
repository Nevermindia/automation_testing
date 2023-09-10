package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveTwoArticlesAndDeleteOne(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearch();
        String article_search = "Java";
        searchPageObject.inputSearchLine(article_search);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElementPresent("Java (programming language)");
        String name_of_folder="Learning programming";
        articlePageObject.saveArticleToNewFolder(name_of_folder);
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.returnToSearchResultsFromTheArticle();
        searchPageObject.clickByArticleWithSubstring("High-level programming language");
        articlePageObject.waitForTitleElementPresent("JavaScript");
        articlePageObject.saveArticleToExistingFolder(name_of_folder);
        navigationUI.clickByViewListButton();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete("Java (programming language)");
        myListsPageObject.waitForSavedArticleAndClick("High-level programming language");
        articlePageObject.assertTitleHasText("JavaScript");

    }


}
