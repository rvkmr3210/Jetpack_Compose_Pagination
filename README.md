# Jetpack Compose Pagination: Implementing Infinite Scrolling with Paging 3

This sample project demonstrates how to implement pagination in a Jetpack Compose app using GitHub's public APIs. The app fetches data from GitHub's users API in paginated chunks and displays the list of users. As the user scrolls through the list, more users are dynamically loaded, providing a smooth and efficient user experience.

## Features
- Pagination with Jetpack Compose 
- Seamless loading of data as the user scrolls 
- Integration with GitHub's public APIs
- Minimalistic and user-friendly UI

## Libraries Used

- [Jetpack Compose](https://developer.android.com/jetpack/compose): Modern UI toolkit for building native Android apps
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): Library for handling pagination and large datasets
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android): Dependency Injection
- [Retrofit](https://square.github.io/retrofit/): HTTP client for API communication
- [Gson](): JSON serialization/deserialization library
- [Coil](https://github.com/coil-kt/coil#jetpack-compose): Image loading library 
  

## Pagination Implementation

- Create a `PagingSource` class that defines how to load data in chunks. This class will handle fetching the data for each page using `Retrofit` to interact with GitHub's API.
   ```kotlin
   
  class UsersPagingSource @Inject constructor(val apiService: ApiService) :
    PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(ApiEndPoint.PER_PAGE_LIMIT)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(ApiEndPoint.PER_PAGE_LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val since = params.key ?: 1
            val response =
                apiService.getUsersList(
                    since = since,
                    perPage = params.loadSize/*ApiEndPoint.PER_PAGE_LIMIT*/
                )
            LoadResult.Page(
                data = response,
                prevKey = if (since == 1) null else since.minus(ApiEndPoint.PER_PAGE_LIMIT),
                nextKey = if (response.isEmpty()) null else since.plus(ApiEndPoint.PER_PAGE_LIMIT),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
  }
   
   ```
   
- Configure the Paging data flow in the view model using `Pager` and `Flow`.
```kotlin

    val usersList = Pager(
        config = PagingConfig(
            pageSize = ApiEndPoint.PER_PAGE_LIMIT,
            enablePlaceholders = true,
        ),
        initialKey = 1,
        pagingSourceFactory = {
            UsersPagingSource(apiService)
        }).flow.cachedIn(viewModelScope)

```



- Collect the paging data flow in the Composable as `collectAsLazyPagingItems()` and use it to display the paginated list using Jetpack Compose's LazyColumn.
```kotlin
        val users = usersViewModel.usersList.collectAsLazyPagingItems()

```

```kotlin
 LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(),
            state = listState,
            contentPadding = PaddingValues(20.dp)
        ) {

            items(count = users.itemCount) { index: Int ->
                val user = users[index]
                UserListItem(navController, user!!)
            }

            when (val state = users.loadState.prepend) { //FIRST LOAD

                is LoadState.Error -> {
                    error(state.error.message ?: "")
                    Log.d("PagingState - prepend - ", "Error")

                }

                is LoadState.Loading -> {
                    Log.d("PagingState - prepend - ", "Loading")

                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - prepend - ", "Not Loading")
                }
            }

            when (val state = users.loadState.refresh) {
                //FIRST LOAD
                is LoadState.Error -> {
                    Log.d("PagingState - refresh - ", "Error")

                    error(state.error.message ?: "")
                }

                is LoadState.Loading -> {
                    Log.d("PagingState - refresh - ", "Loading")

                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - refresh - ", "Not Loading")
                }
            }

            when (val state = users.loadState.append) { // Pagination
                is LoadState.Error -> {
                    Log.d("PagingState - append - ", "Loading")
                    error(state.error.message ?: "")
                }

                is LoadState.Loading -> {
                    Log.d("PagingState - append - ", "Loading")
                    loading()
                }

                is LoadState.NotLoading -> {
                    Log.d("PagingState - append - ", "Loading")
                }

            }
        }
```


## API Configuration
This sample project uses GitHub's public APIs to fetch Users. No authentication is required for accessing public API's. If you plan to use authenticated APIs or APIs with higher rate limits, make sure to configure your authentication credentials properly.

## Contributions
Contributions to this project are welcome! If you find any issues or have ideas for improvements, feel free to open an issue or submit a pull request.

## Disclaimer
This project is for educational and demonstration purposes only. It may not be suitable for production use.







