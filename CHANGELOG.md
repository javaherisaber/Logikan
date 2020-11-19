# 1.7
- Remove JalaliCalendar in favor of JalaliCalendar library
- Abstract out prefetchSize in RecyclerView paginator
- Add loading page and delete paginator in RecyclerViewPaginator
- Remove OfflineMockInterceptor in favor of MockFit library
- Unbundled from master project and upgrade versions.gradle
- Update LICENSE to GNU GPL V3
- Update package name to ir.logicbase.core

# 1.6
- Create Context.colorDrawableAt extension function
- Create ConfigurationUtils Object
- Handle http terminal errors
- Create style for iconic imageView
- Add image to ErrorData and propagate to outer layer
- Enlarge close icon in core_view_network_unavailable.xml
- Hide network offline alert when onBackPressed
- Handle 422 unprocessable entity
- Add decodeUnicode() extension on String
- Create http logger to print in console with proper format
- Create empty state for NetworkApiResponse
- Add emptyListData to single, list and paged state
- Remove logger from mock interceptor due to performance impact
- Move parse deep link logic to StringExtensions.kt
- Remove Terminal and NonTerminal terminology from data layer
- Fix swallowed unprocessable entity exception in DataException
- Inherit common properties from DataOutcome in it's children
- Add dataToBindingId map in SingleDataAdapter
- Migrate to Kotlin 1.4.10
- Use Kotlin 1.4 SAM conversion lambda
- Remove stdlib dependency from build.gradle
- Add proguard-rule to keep Gson SerializedName to support R8

# 1.5
- Add http method support to OfflineMockInterceptor
- Fix MessageFormat unneeded comma separated number in JalaliCalendar
- Rename jalaliDateTimeLabel to jalaliUnixDateTimeLabel in data binding
- Add DividerItemDecoration to RecyclerViewBindingAdapter
- Add Bearer token string extension function
- Create ActivityExtensions with showTryAgainErrorToast function
- Add getChangePayload() to SimpleDiffCallback to support partial update
- Add spinnerBindingAdapters for onItemSelection and two way data binding
- Add code and index to Gender and throw IllegalArgumentException
- Add two way selectedIndex to SpinnerBindingAdapter
- Create decorator for recyclerView item margin
- Fix GridMarginItemDecoration issue on spanCounts higher than 2
- Replace deprecated LifecycleRegistry.markState method with currentState
- Add kotlinOptions jvmTarget 1.8 and lifecycle-common-java8 dependency
- Delete ApplicationInfoInterceptor because it's project specific class
- Remove nullablity from String value in SecureSharedPreferences
- Add backstack support to openChildFragment
- Display zero in TextView.price attribute instead of hiding it
- Use childFragment.java.SimpleName as tag for fragmentManager back stack
- Add text with label binding adapter and sheba code converter to core and change default margin size in core
- Add localized label to gender and sheba code converter
- Add show toast to base fragment and check list nullability to collections
- Add label attribute to Jalali binding adapters on TextView
- Add compareTo to JalaliCalendar and Clock to support comparison
- Change `msg` field in Network ErrorData to `message`
- Use HasAndroidInjector in BaseActivity & BaseFragment (dagger interface)
- Add andSeparatedText to TextViewBindingAdapters
- Add toolbar extension functions to FragmentExtensions
- Add getClock by formatted string to Clock.kt
- Add chip decoration style and ChipGroup data binding adapter
- Add chipEntry style and data binding adapters
- Move `inputType=textNoSuggestion` to EditText.Disabled in styles_widget
- Add Integer.toZeroTail extension function
- Add network endpoint path with list of id's to OfflineMockInterceptor
- Generalize createCommaSeparatedValues function to Collection class
- Add utility functions to DayOfWeekPersian for ordering purposes
- Add inflateEntryChip extension function to ChipGroupBindingAdapters
- Add isTimePeriodsOverlap function to Clock
- Add hideKeyboard() extension function on Fragment
- Implement network connectivity receiver as LiveData class
- Remove redundant CustomBindingAdapters.kt
- Add lineEnabled attribute to CoreColoredStrikeThroughTextView
- Add hasAvailability attribute to price binding adapter
- Add hideIfNullOrZero binding adapter on View
- Add choice chipGroup binding adapter
- Add setText with string resId binding adapter and ignore zero value
- Add selected choice chip id to ChipGroupBindingAdapter
- Add plus & minus operator and formatWithNoLeadingZero to Clock
- Handle network connectivity in baseActivity and baseFragment
- Mark attachNetworkAvailabilityContainer and attachBaseViewModel as open
- Create binding adapter to set imageView tint with condition
- Create ExtendedFab progress bar binding adapter
- Fix getJalaliCalendar from timestamp function to use built-in methods
- Show a message on empty state in lists
- Hide emptyState when recyclerView is not empty anymore
- Create extendedFab widget style
- Fix font blinks in chip groups
- Create textInputLayout dense style
- Create NumberInlines.whenNotNullOrZero function
- Support for static stringArray in popup menu binding adapter
- Override SharedPreferences.getString() from Platform 29
- Create sumByLong extension function
- Move toSeparatedThousands() from StringUtils to NumberExtensions
- Remove minTouchTargetSize in Chip.Decoration
- Replace booleanToVisibility with BindingAdapter to better support in AS
- Add Fragment.setToolbarTitle overloaded function with @StringRes param
- Add commaSeparatedText binding adapter to TextViewBindingAdapters
- Remove deprecated function StringUtil.numberToSeparatedThousands()
- Add listChange listener to SingleDataAdapter
- Use built-in app:iconTint attribute to set progressDrawable color
- Add MaterialButtonBinding setBackgroundTint binding adapter
- Create binding adapter for EditText keyboard actions
- Add toolbar and navigation extension functions to FragmentExtensions
- Create View.setVisibileInVisible() binding adapter
- Use viewLifeCycleOwner instead of this for liveData observer methods
- Add removeListChangeListener function to SingleDataAdapter
- Add Fragment.removeChildFragment() extension
- Move setCircularProgressDrawable() from ExtendedFab to MaterialButton
- Create DataReactiveUtil class to handle transform and map in repository
- Extend AppCompatTextView in CoreColoredStrikeThroughTextView class
- Create LICENCE file
- Add number transcript and thousands separator to edit text
- Move composeWith to DataReactiveUtil and add typealias for common uses
- Implement base class for BottomSheetDialog and Preference fragments
- Set singleSelection by default for choice chipGroup binding adapter
- Remove redundant @Module & @JvmStatic in dagger module companion object
Dagger 2.26+ ignores these annotations
- Add proguard rule to -dontwarn kotlin coroutines while release apk
- Log mock json in offline network
- Clear editText value when resId is set to null in data binding adapter
- Use inflateViewWithSynchronizedFont in chipGroup binding adapters
- Create insideParentheses binding adapter on TextView
- Add iranLegalValues companion method to Gender.kt
- Create api token qualifier
- Add UpdateData to NetworkApiResponse to propagate through data layer
- Fix networkConnectivity becomes online at app startup
Although we haven't had offline state before
- Add deepLinkPackage field to UpdateData to open specified app directly
- Add isDebuggable extension function on Context
- Create typealias for OneShotEvent
- Add zeroValueMessage attribute to TextViewBindingAdapters.price
- Create helper classes for paging
- Change dataList to data in DataOutcome.kt
Now data with object type also supports pagination
- Accept nullable argument in Fragment.setToolbarTitle extension
- Add extension functions to work with Context color and drawables
- Add navigateDeepLink to FragmentExtensions
- Add isNestedScrollingEnabled binding adapter to RecyclerView
- Create CollapseFabOnScrollBehavior to animate extended fab
- Remove Core.Widget.Button.ExtendedCheck style
- Replace OneShotEvent, LiveEvent and SingleLiveEvent with LiveX
- Create binding adapter to set circularProgressDrawable on FAB
- Use drawableEndCompat instead of drawableEnd (support API 19)
- Remove empty view when already exist in ListAdapterBindingAdapters
- Move themes to themes.xml and styles styles.xml
- Add String.toIranMobile() extension function
- Add support for VPN in Connectivity.isOnline()
- Remove null queries in Fragment.navigateDeepLink
- Deprecate Timer.kt in favor of CountDownTimer
- Create TextView.textHtml binding adapter
- Create View.goneIfNullOrZero binding adapter
- Add EmptyState view as first child in RecyclerView
- Create BitmapExtensions to convert Base64
- Create IntentExtensions to requireData on Intent
- Create String.mimeTypeForUrl extension function
- Move pickImage and Bitmap function to extensions
- Add isZeroIndex attribute to recyclerView emptyState
- Add pixel/dp conversion methods on Context
- Enlarge extended fab to 56 dp when shrunken
- Add day night theme

# 1.4
- Add unix and iso timestamp types
- Request path (eg. product/{id}) support in OfflineMockInterceptor
- BaseViewModel.emitUnitEvent() deprecated in favor of extension function
- Add expandableTextView anchor, binding adapter
- Add countDown and countUp functionality to Clock
- Create CoreColoredStrikeThroughTextView
- Add Unified price binding adapter

# 1.3.3
- Create EditTextExtensions
- Mark LoginVerifyBaseViewModel and LoginRequestBaseViewModel as abstract
- Remove non reusable properties from LoginVerifyBaseViewModel

# 1.3.2
- Remove fontFamily from AppTheme, it was shadowing typography
- Create LoginRequestBaseViewModel and LoginVerifyBaseViewModel
- Add @CallSuper to BaseViewModel.onCleared()
- Add Minute:Second label type to Clock
- Create TextWatcher for chained EditTexts
- Create NavigationExtensions, FragmentExtensions and StringUtils

# 1.3.1
- Add collections extensions
- Add payload support to recycler view classes to enable partial updates

# 1.3
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

# 1.2.3
- Unify data binding classes with good packaging strategy
- Move repository interface classes to data/domain/repository

# 1.2.2
- Change dokka output directory to apply new documentation strategy
- Enable correctErrorTypes for kapt to fix some generated code while build

# 1.2.1
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

# 1.2.0
- Stable release with clean architecture

# 1.2.0-alpha
- error resolution stub for presentation layer (need to implement an interface inside BaseActivity or any other suitable place)

# 1.1.0
- new error handler with more flexible design
- mock interceptor

# 1.0.4
- refactor data package with new naming
- add dokka support
- add kapt incremental annotation processing, parallel compile and compile avoidance to increase build speed
- delegate SecureSharedPreferences secret key to caller module env

# 1.0.3
- Add stage build type support

# 1.0.2
- Add Java inter-op to object classes

# 1.0.1
- Move to Bitbucket private repository

# 1.0.0
- Add to jitpack public repository
- Basic setup on project
- Create base style to enable us extend our own style better
- Convert some parts of library to Kotlin