SIT7085.1C : Sports News Feed App

A news reader application with hardcoded dummy data to display sports news, filter stories, and save user bookmarks locally.

### Key Features
* **Dual-Axis Scrolling:** The Home screen features a horizontal `RecyclerView` for "Top Stories" and a vertical `RecyclerView` for "Latest News".
* **Dynamic Search & Filtering:** A `SearchView` allows users to filter the news feed by category (e.g., Football, Basketball, Cricket) in real-time.
* **Detail View & Related Content:** Tapping any news item opens a Detail Fragment using `Bundle` arguments. The detail page dynamically generates a list of "Related Stories" based on the current article's sport category.
* **Local Bookmarking:** Users can save their favorite articles. Bookmarked titles are persisted locally using `SharedPreferences`.
* **Bookmarks Library:** A dedicated Bookmarks Fragment retrieves the saved data and displays the user's custom reading list.
* **Seamless Navigation:** Utilizes Fragment transactions with proper back-stack management to ensure a smooth user experience.

