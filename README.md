# AndroidProjectCore

Core, base and utility functionality required by an android project

## Changelog
### 1.4
- Add unix and iso timestamp types
- Request path (eg. product/{id}) support in OfflineMockInterceptor
- BaseViewModel.emitUnitEvent() deprecated in favor of extension function
- Add expandableTextView anchor, binding adapter
- Add countDown and countUp functionality to Clock
- Create CoreColoredStrikeThroughTextView
- Add Unified price binding adapter

### 1.3.3
- Create EditTextExtensions
- Mark LoginVerifyBaseViewModel and LoginRequestBaseViewModel as abstract
- Remove non reusable properties from LoginVerifyBaseViewModel

### 1.3.2
- Remove fontFamily from AppTheme, it was shadowing typography
- Create LoginRequestBaseViewModel and LoginVerifyBaseViewModel
- Add @CallSuper to BaseViewModel.onCleared()
- Add Minute:Second label type to Clock
- Create TextWatcher for chained EditTexts
- Create NavigationExtensions, FragmentExtensions and StringUtils

### 1.3.1
- Add collections extensions
- Add payload support to recycler view classes to enable partial updates

### 1.3
- Add api response latency to OfflineMockInterceptor
- Add success property to Single, List and PagedList data in DataOutcome
- Create CustomNavigationUI extensions to handle navigationView's menuItem
- Delete redundant classes and listeners
- Add logging to classes with lifecycle
- Add Gender and SpinnerBindingAdapter
- Fix JalaliCalendar conversion issues
- Add timestamp support for Clock and JalaliCalendar
- Replace `Live` prefix with `OneShot` in LiveEvent for better naming
- Create ListExtensions

### 1.2.3
- Unify data binding classes with good packaging strategy
- Move repository interface classes to data/domain/repository

### 1.2.2
- Change dokka output directory to apply new documentation strategy
- Enable correctErrorTypes for kapt to fix some generated code while build

### 1.2.1
- Delete onOptionsItemSelected callback cause navigation is handling this
- Fix naming conventions for resources
- Add vertical line separator layout
- Add code of conduct for resources
- Fix locale not changing
- Add good defaults to GraphicUtils class
- Create GenericRepository to access generic api
- Create Event and EventObserver for presentation layer communication
- Reorder constructor parameters in Disposable's to better suit our needs
- Create better generic ViewHolder and Adapter for recyclerView
- Add navigationArchitectureComponent, circleImageView, Glide dependencies
- Add negative and neutral buttons with a neutralCallback for AlertDialog
- Make disposableContext visibility to public so we can use in platform
- Add go to location settings AlertDialog
- Add emitUnitEvent extension to BaseViewHolder
- Mark errorStateObserver as nonNull and mandatory property in observers
   anyone who need data must know how to handle it's errors
- Remove terminal error handler from BaseActivity (each app has it's own)
- Add AppExecutor to SingleDataAdapter diffUtil and fix binding issue
   appExecutor is needed to calculate differences in diffUtil off the main thread

### 1.2.0
- Stable release with clean architecture

### 1.2.0-alpha
- error resolution stub for presentation layer (need to implement an interface inside BaseActivity or any other suitable place)

### 1.1.0
- new error handler with more flexible design
- mock interceptor

### 1.0.4
- refactor data package with new naming
- add dokka support
- add kapt incremental annotation processing, parallel compile and compile avoidance to increase build speed
- delegate SecureSharedPreferences secret key to caller module env

### 1.0.3
- Add stage build type support

### 1.0.2
- Add Java inter-op to object classes

### 1.0.1
- Move to Bitbucket private repository

### 1.0.0
- Add to jitpack public repository
- Basic setup on project
- Create base style to enable us extend our own style better
- Convert some parts of library to Kotlin
