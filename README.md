# AndroidProjectCore

Core, base and utility functionalities required by an android project

## Changelog
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
