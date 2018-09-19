/*
 * Copyright 2018 Arunkumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package `in`.arunkumarsampath.transitionx.transition.changetext

import android.support.annotation.IntDef

@IntDef(value = [
    ChangeText.CHANGE_BEHAVIOR_IN,
    ChangeText.CHANGE_BEHAVIOR_OUT,
    ChangeText.CHANGE_BEHAVIOR_OUT_IN,
    ChangeText.CHANGE_BEHAVIOR_KEEP
])
@Retention(AnnotationRetention.SOURCE)
annotation class ChangeTextBehavior