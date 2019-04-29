using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Activity.Intent.RNActivityIntent
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNActivityIntentModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNActivityIntentModule"/>.
        /// </summary>
        internal RNActivityIntentModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNActivityIntent";
            }
        }
    }
}
