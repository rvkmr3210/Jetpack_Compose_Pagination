# Jetpack Compose Pagination: Implementing Infinite Scrolling with Paging 3

This sample project demonstrates how to implement pagination in a Jetpack Compose app using GitHub's public APIs. The app fetches data from GitHub's users API in paginated chunks and displays the list of users. As the user scrolls through the list, more users are dynamically loaded, providing a smooth and efficient user experience.

<H2>Features: </H2>
<ul>
  <li> Pagination with Jetpack Compose </li>
  <li> Seamless loading of data as the user scrolls </li>
  <li> Integration with GitHub's public APIs </li>
  <li> Minimalistic and user-friendly UI </li>
</ul>

<H2>Libraries Used</H2>
<ul>
  <li> <B>Jetpack Compose</B> - Modern UI toolkit for building native Android apps</li>
  <li> <B>Paging 3</B> - Library for handling pagination and large datasets</li>
  <li> <B>Hilt</B> - Dependency Injection</li>
  <li> <B>Retrofit</B> - HTTP client for API communication</li>
  <li> <B>Gson</B> - JSON serialization/deserialization library</li>
  <li> <B>Coil</B> - Image loading library</li>
</ul>

<H2>Pagination Implementation</H2>
<ol>
  <li>
    Create a PagingSource class that defines how to load data in chunks. This class will handle fetching the data for each page using Retrofit to interact with GitHub's API.
    <br>
  <img width="893" alt="Screenshot 2023-08-04 at 10 41 55 AM" src="https://github.com/rvkmr3210/ComposePagination/assets/37369324/909049f2-8319-4d06-8eae-8986c8528499">
  </li>
  <li>
   
  Configure the Paging data flow in the view model using Pager and Flow.
  <img width="786" alt="Screenshot 2023-08-04 at 10 42 20 AM" src="https://github.com/rvkmr3210/ComposePagination/assets/37369324/46d7407e-e12c-4131-9759-dc83cac2f6c3">

  </li>
  <li> Collect the paging data flow in the Composable and use it to display the paginated list using Jetpack Compose's LazyColumn.
    <br>
  <img width="758" alt="Screenshot 2023-08-04 at 10 43 44 AM" src="https://github.com/rvkmr3210/ComposePagination/assets/37369324/51e99d01-ff03-4f0f-9f13-02cd26f367e2">
 <br>
  <img width="773" alt="Screenshot 2023-08-04 at 10 43 26 AM" src="https://github.com/rvkmr3210/ComposePagination/assets/37369324/0a5823d4-f33c-41ab-bfaa-2c4677e7b178">

  </li>
</ol>

<H2>API Configuration</H2>
This sample project uses GitHub's public APIs to fetch repositories. No authentication is required for accessing public repositories. If you plan to use authenticated APIs or APIs with higher rate limits, make sure to configure your authentication credentials properly.

<H2>Contributions</H2>
Contributions to this project are welcome! If you find any issues or have ideas for improvements, feel free to open an issue or submit a pull request.

<H2>Disclaimer</H2>
This project is for educational and demonstration purposes only. It may not be suitable for production use.







