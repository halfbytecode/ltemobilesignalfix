package me.hbc.ltemobilesignalfix;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.callbacks.XCallback;
import de.robv.android.xposed.XposedBridge;

public class XposedHook implements IXposedHookLoadPackage
{
	private static boolean DEBUG = false;

	@Override
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable
	{
		// if (!lpparam.packageName.equals("android.telephony"))
		// 	return;
		
		XposedHelpers.findAndHookMethod(
			"android.telephony.SignalStrength",
			lpparam.classLoader,
			"useOnlyRsrpForLteLevel",
			// float[].class, long.class,
			new XC_MethodReplacement() {
				@Override
				protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {

					if (DEBUG) XposedBridge.log("INSIDE useOnlyRsrpForLteLevel()");
					
					return true;
				}
			});
	}

}