# SIT708 Task 5.1C - Sports News Feed App

## Project Overview
This repository contains the Subtask 1 for the SIT708 unit (Task 5.1C). It is a Android sports news reader application that using hardcoded data to display news, filter stories by category, and allow users to save bookmarks locally. The app demonstratesUI design, Fragment navigation, and local data persistence.

## Features
* **Dual-Axis Scrolling:** The Home screen features a horizontal `RecyclerView` for "Top Stories" and a vertical `RecyclerView` for "Latest News".
* **Dynamic Search & Filtering:** A built-in `SearchView` allows users to filter the news feed by category (e.g., Football, Basketball, Cricket) in real-time.
* **Detail View & Related Content:** Tapping any news item navigates to a Detail Fragment, passing data via `Bundle` arguments. The detail page dynamically generates a list of "Related Stories" based on the current article's sport category.
* **Local Bookmarking:** Users can save their favorite articles. Bookmarked titles are persisted locally using Android's `SharedPreferences`.
* **Bookmarks Library:** A dedicated Bookmarks Fragment retrieves the saved `SharedPreferences` data and displays the user's custom reading list.


## How to run the project
1. **Clone the repository** to your local machine
2. Open the project in Android Studio.
3. Sync Gradle to ensure all standard Android dependencies are loaded.
4. Click the green Run 'app' button 
