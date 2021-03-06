##Linux Command Bibliotheca for Android
The app delivers you thousands of manual pages of Linux terminal commands, a bunch of useful one-line scripts, general terminal tips and a Linux quiz.

Download from Play Store https://play.google.com/store/apps/details?id=com.inspiredandroid.linuxcommandbibliotheca

![enter image description here][1]
![enter image description here][2]
![enter image description here][3]
![enter image description here][4]
![enter image description here][5]

###Sample usage
Start library from another app

    Intent intent = new Intent("com.inspiredandroid.linuxcommandbibliotheca.CommandBibliothecaActivity");
    startActivityForResult(intent, REQ_PICK_COMMAND);

Read result from onActivityResult

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_PICK_COMMAND) {
	        if (resultCode == Activity.RESULT_OK) {
		        String command = data.getStringExtra(CommandBibliothecaActivity.EXTRA_COMMAND);  
				String base64Icon = data.getStringExtra(CommandBibliothecaActivity.EXTRA_ICON);
            }
        }
    }

###Man pages database
Here is the bash script that I used to generate the man pages database:
https://gist.github.com/SimonSchubert/6f0715610cb37f4fda12

###Thanks to
http://letsgokoyo.com/ - Icons, Tux images

http://www.commandlinefu.com/

https://github.com/jgilfelt/android-sqlite-asset-helper

###License

> Licensed under the Apache License, Version 2.0 (the "License"); you
> may not use this file except in compliance with the License. You may
> obtain a copy of the License at
>
>    http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software
> distributed under the License is distributed on an "AS IS" BASIS,
> WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
> implied. See the License for the specific language governing
> permissions and limitations under the Licen


  [1]: https://raw.githubusercontent.com/SimonSchubert/LinuxCommandBibliotheca/master/screen-1.png
  [2]: https://raw.githubusercontent.com/SimonSchubert/LinuxCommandBibliotheca/master/screen-2.png
  [3]: https://raw.githubusercontent.com/SimonSchubert/LinuxCommandBibliotheca/master/screen-3.png
  [4]: https://raw.githubusercontent.com/SimonSchubert/LinuxCommandBibliotheca/master/screen-4.png
  [5]: https://raw.githubusercontent.com/SimonSchubert/LinuxCommandBibliotheca/master/screen-5.png
