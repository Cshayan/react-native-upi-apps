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
