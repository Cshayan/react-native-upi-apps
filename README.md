# react-native-upi-apps

A library to detect any installed UPI apps on a user's device and get their information on React Native side (Android only)

## Installation

```sh
npm install react-native-upi-apps
```

## Library Info
In Android for your react-native app, if you want to know which UPI apps are installed on the device, make use of this library. Just install  the library using `npm` or `yarn` and add the below snippet in your `AndroidManifest.xml` file within the `manifest` tag -

```js
<queries>
    <package android:name="com.phonepe.app" />   <!-- phonpe  -->
    <package android:name="com.google.android.apps.nbu.paisa.user" /> <!-- gpay  -->
    <package android:name="net.one97.paytm" />  <!-- paytm  -->
    <package android:name="in.org.npci.upiapp" /> <!-- bhim  -->
    <package android:name="in.amazon.mShop.android.shopping" />   <!-- amazonpay  -->
    <package android:name="com.upi.axispay" />  <!-- axisbank  -->
    <package android:name="com.dreamplug.androidapp" /> <!-- cred  -->
    <package android:name="com.csam.icici.bank.imobile" /> <!-- icici  -->
    <package android:name="com.myairtelapp" /> <!-- my airtel -->
</queries>
```
You can add more upi apps with their package name if you want.

## Usage

```js
import React, { useEffect, useState } from 'react';
import { StyleSheet, View, Text, Image } from 'react-native';
import { getInstalledUPIAppList } from 'react-native-upi-apps';
import type { UPIApp } from 'src/types';

export default function App() {
  const [result, setResult] = useState<UPIApp[]>([]);

  useEffect(() => {
    const getInstalledUPIApps = async () => {
      const appList = await getInstalledUPIAppList();
      setResult(appList);
    };

    getInstalledUPIApps();
  }, []);

  return (
    <View style={styles.container}>
      {result?.map((app) => (
        <View style={styles.listView}>
          <Image
            source={{ uri: `data:image/png;base64, ${app?.icon}` }}
            resizeMode="contain"
            style={styles.icon}
          />
          <Text>{app?.name}</Text>
        </View>
      ))}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  listView: {
    flexDirection: 'column',
    flex: 1,
  },
  icon: {
    width: 50,
    height: 50,
  },
});

```

## Library methods - 

```js
1. getInstalledUPIAppList
```

This method returns a promise with all the installed UPI apps in your device. The response will be an array of object of all the app list with. Each object will have the following 3 properties that can be used - 

```js
name: string;
packageName: string;
icon: string;
```

| Property | Detail |
| --------------- | --------------- |
| name | It denotes the name of the UPI app | 
| packageName | It denotes the package name of the UPI app | 
| icon | It is the base64 encoded string denoting the logo of the UPI app | 

## Note
This library is supported only for Android devices.

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
