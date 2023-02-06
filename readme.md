## Zattoo - Android Code Challenge

Android coding challenge solution

* Dependency injection migration to dagger hilt

* Rearchitected the existing app to clean architecture inorder to improve the maintainability and testability

* View model with screen states and events is introduced to manage the UI states. 

* The UI is migrated to Jetpack compose

* Navigation component for jetpack compose is user for screen navigation

* Unit testing for domain layer is implemented with mockito and google truth library

* The network disconnected/connected states are handled with the view model. 

* Issue fixes - The network "Connected" message was not showing when the connection is back

* Kotlin version migrated to 1.6.21 and build error is fixed. 

* Created abstraction for network util class to avoid the existing concrete dependency. 

* Auto refresh is implemented when the network connection is back. 

Folder structure

* Common package contains all the shared files and utilities like converter, network util, constants and resource generic class
  
* UI is moved to presentation package
  
* domain layer contains the repository abstraction and entity or the movie
  
* Data layer contains the remote network calls and DTOs
  
* ui.theme contains the compose theme implementation