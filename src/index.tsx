import { NativeModules, Platform } from 'react-native';
import type { UPIApp } from './types';

const LINKING_ERROR =
  `The package 'react-native-upi-apps' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const UpiApps = NativeModules.UpiApps
  ? NativeModules.UpiApps
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export async function getInstalledUPIAppList(): Promise<UPIApp[]> {
  return UpiApps.getInstalledUPIAppList();
}
