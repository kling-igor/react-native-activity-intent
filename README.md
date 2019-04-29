
# react-native-activity-intent

## Getting started

`$ npm install react-native-activity-intent --save`

### Mostly automatic installation

`$ react-native link react-native-activity-intent`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-activity-intent` and add `RNActivityIntent.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNActivityIntent.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNActivityIntentPackage;` to the imports at the top of the file
  - Add `new RNActivityIntentPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-activity-intent'
  	project(':react-native-activity-intent').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-activity-intent/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-activity-intent')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNActivityIntent.sln` in `node_modules/react-native-activity-intent/windows/RNActivityIntent.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Activity.Intent.RNActivityIntent;` to the usings at the top of the file
  - Add `new RNActivityIntentPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNActivityIntent from 'react-native-activity-intent';

// TODO: What to do with the module?
RNActivityIntent;
```
  