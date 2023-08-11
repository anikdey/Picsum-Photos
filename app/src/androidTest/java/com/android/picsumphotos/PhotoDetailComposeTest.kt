package com.android.picsumphotos

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.android.domain.model.PhotoModel
import com.android.picsumphotos.common.Constants
import com.android.picsumphotos.photolist.screens.PhotoDetailsScreen
import com.android.picsumphotos.ui.theme.PicSumPhotosTheme
import org.junit.Rule
import org.junit.Test

class PhotoDetailComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    val landScapeModel = PhotoModel("91", "Jennifer Trovato", 3504, 2336, "")

    val portraitModel = PhotoModel("92", "Rafael Souza", 2000, 2368, "", true)

    @Test
    fun column_contains_portrait_test_tag() {
        composeTestRule.setContent {
            PicSumPhotosTheme {
                PhotoDetailsScreen(photoModel = portraitModel)
            }
        }
        composeTestRule.onNodeWithText(portraitModel.author).assertIsDisplayed()
        val column = composeTestRule.onNode(hasTestTag(Constants.portraitTag), useUnmergedTree = true)
        column.assertIsDisplayed()
    }
    @Test
    fun column_contains_landscape_test_tag() {
        composeTestRule.setContent {
            PicSumPhotosTheme {
                PhotoDetailsScreen(photoModel = landScapeModel)
            }
        }
        composeTestRule.onNodeWithText(landScapeModel.author).assertIsDisplayed()
        val column = composeTestRule.onNode(hasTestTag(Constants.landscapeTag), useUnmergedTree = true)
        column.assertIsDisplayed()
    }

}