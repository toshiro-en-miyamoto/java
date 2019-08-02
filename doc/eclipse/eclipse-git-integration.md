Integrating your local Eclipse IDE and github.com

* let Eclipse generate an SSH key
  * Eclipse > Preferences > General > Network Connections > SSH2
    * Key Management > Generate RSA, Save Private Key at ~/.ssh
* at your github.com account
  * register the public key of the SSH
  * create a repository, e.g. "project1", initialized with README.md
* configure Eclipse EGit
  * Eclipse > Preferences
    * Team > Git > Configuration > User settngs
      * user.name: your name defined in your github account
      * user.email: your email defined in your github account
    * Team > Git > Configuration > System settngs
      * make sure - Location: /private/etc/gitconfig
* let clone the project1 repository to the local filesystem
  * with Git perspective
  * clone a repository
    * Source Git Repository
      * URI: https://github.com/your-git-account/project1.git
    * Authentication
      * User ID and password of your github.com account
      * check "Store in Secure Store"
    * Local Destination
      * Directory: /Users/toshiro/git/project1
    * then, the Git Repositories explorer will show
        project1 [master] - /Users/toshiro/git/project1/.git
        > Branches
          Tags
        > References
        > Remotes
          > origin
        > Working Tree - /Users/toshiro/git/project1
          > .git
          README.md

* create a Java project "HelloJava"
  * with Java perspective
  * File > New Java Project
    * C++ Project
      * Project name: HelloMacGCC
      * Project type: Executable > Hello World C++ Project
      * make sure macOS GCC in Toolchain
* add the project to the Git repository
  * with Java perspective
  * right-click on the project > Team > Share Project ...
  * Configure Git Repository
    * Repository: wxWidgets - /Users/toshiro/git/wxWidgets/.git
    * Working tree: /Users/toshiro/git/wxWidgets
    * Project: HelloMacGCC (checked)
    * Current Location: /Users/toshiro/eclipse-workspace/wxWidgets/HelloMacGCC
    * Target Location: /Users/toshiro/git/wxWidgets/HelloMacGCC
  * then Project Explorer shows
    HelloMacGCC [wxWidgets master]
    > Includes
      > /Library/Developer/CommandLineTools/usr/include
      > /Library/Developer/CommandLineTools/usrinclude/c++/v1
      > /Library/Developer/CommandLineTools/usr/lib/clang/9.0.0/include
      > /Library/Frameworks
      > /System/Library/Frameworks
      > /Usr/include
    > src
      > HelloMinGW.cpp
    > .settings
      > language.settings.xml
    > .cproject
    > .project
* configure Project Explorer to display .gitignore file
  * with C/C++ perspective
  * View Menu; right on the Project Explorer's tab
  * Filters and Customization ...
  * Filters tab - uncheck [.* resources]
  * Project Explorer will show .gitignore file after build
* build the debug version of HelloMacGCC
  * with C/C++ perspective
  * Build > Debug
  * now Project Explorer shows
    HelloMinGW [wxWidgets master]
    > Binaries
      > HelloMacGCC - [x86_64/le]
    > Includes
    > src
    > Debug
      > src
        > HelloMinGW.o - [x86/le]
      > HelloMinGW.exe - [x86/le]
    > .settings
      > language.settings.xml
    > .cproject
    > .gitignore
    > .project
  * open the .gitignore file to see if /Debug is included in it
* add the settings and the source files to Git index
  * with C/C++ perspective
  * right-click on the project
  * Team > Add to Index, the following will be added
    * /src [folder]
    * /.settings [folder]
    * /.cproject [file]
    * /.gitignore [file]
    * /.project [file]
* commit the project and push it to github.com
  * with C/C++ perspective
  * right-click on the project
  * Team > Commit
  * Git Staging pane
    * ensure files added to index appear in "Staged Changes"
    * fill in a message in "Commit Message"
    * Commit and Push ...
  * Push Result dialog will open
  * if it looks good, check the github.com repository 
