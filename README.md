# AdvancedRecyclerView

This Repository contains an example app for an advanced recyclerview example for android. Which contains some amazing features like ads between recyclerview items,search through this items,load items from json and much more :-)

## Getting Started

To use this example in your app just clone the app and import it in android studio and follow installation below :-)

### Prerequisites

Android Studio and a cup of coffee 😉

### Installation according to your requirements

Change the URL at [here](https://github.com/kookybytes/AdvancedRecyclerView/blob/master/app/src/main/java/com/backendme/advancerecycler/fragment/CarLogos.java#L53) 

```
private static final String URL = "Your URL";
```
**in all three fragments.


Change the number of items after an ad in list [here](https://github.com/kookybytes/AdvancedRecyclerView/blob/master/app/src/main/java/com/backendme/advancerecycler/fragment/CarLogos.java#L51) 
```
public static final int ITEMS_PER_AD = 8;
```

Change your AdMob AD UNIT ID [here](https://github.com/kookybytes/AdvancedRecyclerView/blob/master/app/src/main/java/com/backendme/advancerecycler/fragment/CarLogos.java#L54) 
```
private static final String AD_UNIT_ID = "Your AD Unit ID";
```

Change Search Preferance [here](https://github.com/kookybytes/AdvancedRecyclerView/blob/master/app/src/main/java/com/backendme/advancerecycler/fragment/CarLogos.java#L122) 
```
DashboardItem pitem = (DashboardItem) recyclerViewItems.get(pos);
   if(pitem.getName().toLowerCase().contains(text.toLowerCase()))
   {
        temp.add(pitem);
   }

```
Here Change the .getName() to .getType if want to apply search query on Type fields.


Jsons to be uploaded to server can be found [here](http://backendme.com/AdvancedRecyclerview/)

### Features:

* Swipe to refresh(using android.support.v4.widget.SwipeRefreshLayout)
* Load Native AdMob ads between recyclerview items
* Multiple View Types for list items
* Search through Recyclerview items
* Load RecyclerView items from server (JSON Parsing)
* Best example of RecyclerView in a Fragment
* OnClick Actions For RecyclerView Items
* Scrollbars
* Multiple Recyclerview in same activity using Bottom Navigation


### Screenshot
![advance_recyclerview](https://lh3.googleusercontent.com/vryl3Wm-ErIAWhxCZqnJobSl4rC55W5AXNfCjCd98YOTodOWq0hpgCI9K7VZJ1VN1yc=w1366-h637-rw)
![advance_recyclerview](https://lh3.googleusercontent.com/G0_JmdPNpwGmYTnRblpO5Ky3i5mC91KWKTAJfBruAcg0Zi-VawlM4zmtm0Otg3MLIsA=w1366-h637-rw)
![advance_recyclerview](https://lh3.googleusercontent.com/UGzkf7r_9Mz2HsHypUjxaDkzUGhKUoLTDuQpRDJeIkruM9ZTKJSQJn5iHQw3AFS5Qrs=w1366-h637-rw)
![advance_recyclerview](https://lh3.googleusercontent.com/TcOelqOpLSamLLfXToGb1-6JOjQA-vbNl529pOTZfZPVVvCDjxMhxRzRPFvuPmzqeh4=w1366-h637-rw)
![advance_recyclerview](https://lh3.googleusercontent.com/z4dsBaN17Pi52t8OcjP2t3DezZbZdB777wds3VlX5idBDozX1u_-UrXGPMFAxXhEJj3r=w1366-h637-rw)


## Authors

* **Kathan Patel** - *Developer* - [Kathan565](https://github.com/kathan565)
* **Meet Prajapati** - *Developer and Designer* - [meet30997](https://github.com/meet30997)


See also the list of [contributors](https://github.com/kookybytes/AdvancedRecyclerView/contributors) who participated in this project.

## License

``` 
Copyright 2018-present KookyBytes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
If there are someone who I do not mention here,please accept my sincerely appologies and tell me.


## Donation

Donate $9.99
[PayPal](https://www.paypal.me/NAYNABENP/9usd)

Bitcoin Donation is also Accepted


![wallet](http://s32.postimg.org/sdd1oio1t/qrwallet.jpg)

