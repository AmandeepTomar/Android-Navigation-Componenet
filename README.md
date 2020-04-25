# Android Navigation Component

Navigation component is part of android jetpack library. it is used to navigate fragments in apps in fraction less manner.
Basically three main part of navigation component

  - NavigationGraph
  - NavHostFragment
  - NavController

# Project SetUp

  - add dependency to you app.gradle 
    ```
    apply plugin: "androidx.navigation.safeargs.kotlin"
  
    def nav_version = "2.3.0-alpha05"
      // Kotlin
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version" 
    ```
  - Addeding classpath in project.gradle
     ```
      buildscript {
    ext.kotlin_version = '1.3.72'
     ext.nav_version = "2.3.0-alpha05"
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.3'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
        }
    }
    allprojects {
    repositories {
        google()
        jcenter()
      }
    }
    ```

You can also:
  - Add dataBinding to your project by simply adding dataBinding section inside android block of app.gradle 
    ```
    android {
     dataBinding{
        enabled=true
      }
     }
    ```
# Steps    
  - Adding Navigation graph file in project resource 
    - Project -> cretaed new android resource file -> file name ```nav_graph``` resource type ```Navigation```
    - ```nav_graph.xml```file created in app/res/navigation. Its looks like 
      ```
      <?xml version="1.0" encoding="utf-8"?>
       <navigation xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto" android:id="@+id/nav_graph">

      </navigation>
      ```
  - Add ```NavHostFragment``` 
    - To add NavHostFragment go to activity_main.xml file. just go to containers and drag and drop NavHostFragment to your activity_main.xml. 
    - This will add a fragment in xml file with some added properties . its looks like 
     ``` xml 
     
        <fragment
            android:id="@+id/fragment"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_marginStart="1dp"
            android:layout_marginLeft="1dp"
            android:layout_marginTop="1dp"
            android:layout_marginEnd="1dp"
            android:layout_marginRight="1dp"
            android:layout_marginBottom="1dp"
            app:defaultNavHost="true"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:navGraph="@navigation/nav_graph" />
     ```

  - Adding Navigation Destinations 
    - Go to nav_graph.xml file and add new destination. click on + icon to add new destination or create new one . 
    - After adding destination just reopen nav_graph.xml file and now you can see some properties are added. 
  - Adding Navigation Actions 
    - After adding two frgment i.e HomeFragment and SecondFragment we are now set Navigation action on HomeFragmnt to SecondFragment. Just sect destination in ```nav_graph.xml``` file and now can see the updates in same file. 
      ```xml
      <?xml version="1.0" encoding="utf-8"?>
       <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/nav_graph"
        app:startDestination="@id/homeFragment">

       <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.android_navigation_componenet.HomeFragment"
        android:label="fragment_home"
        tools:layout="@layout/fragment_home" >
        <action
            android:id="@+id/action_homeFragment_to_secondFragment"
            app:destination="@id/secondFragment" />
       </fragment>
       <fragment
        android:id="@+id/secondFragment"
        android:name="com.example.android_navigation_componenet.SecondFragment"
        android:label="fragment_second"
        tools:layout="@layout/fragment_second" />
       </navigation>
      ```
     - Here ``<action>``tag will set the action that have ``id``and ``destination``
     
 - Setup action in kotlin code . open homeFragment.kt and set action on button click
   ```Kotlin
    button.setOnClickListener {
    // it as View 
    // findNavController() // use to find the nav Controller 
    // navigate(actionID) 
            it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment)
        }
   ```
 - Pass data between Fragments using budle in HomeFragment
      ```Kotlin
    button.setOnClickListener {
    // it as View 
    // findNavController() // use to find the nav Controller 
    // navigate(actionID) 
            val bundle= bundleOf(USER_INPUT  to homeBinding.editText.text.toString())
            it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment,bundle)
        }
   ```
 - Get data in SecondFragments using budle in HomeFragment
      ```Kotlin
           val inputData=arguments?.getString(USER_INPUT)
   ```   
 - Adding Andmations for Actions in Navigation Component  
   - Animation basically use
   - **ENTER** -> when new fragment enter on new fragment 
   - **EXIT** -> when previous fragment exit 
   - **POP ENTER** -> When press back button on fragment , previous fragment appears.
   - **POP EXIT** -> When press back button current fragment disapprear with this animation 
     ```xml
     //added animation res file in `<action>`tag in silde nav_graph
           <action
            android:id="@+id/action_homeFragment_to_secondFragment"
            app:destination="@id/secondFragment"
            app:enterAnim="@anim/slide_in_left"
            app:exitAnim="@anim/slide_in_right"
            app:popEnterAnim="@anim/slide_out_left"
            app:popExitAnim="@anim/slide_out_right" />
     ```

Special thanks to @Anushka Madusanka, He explain everthing in simple way so everybody can understand. 


