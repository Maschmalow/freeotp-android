[![Build Status](https://travis-ci.org/freeotp/freeotp-android.svg?branch=master)](https://travis-ci.org/freeotp/freeotp-android)

# FreeOTP Backup

This is a fork of [FreeOTP](https://freeotp.github.io) that is only intended as an easy hack to export tokens out. This app is not 
intended for regular use and is not maintained. It merely adds a "Convert backup to URI" option that takes a regular FreeOTP backup and converts it to a list of URIs in a text file,
compatible with [FreeOTP+](https://github.com/helloworld1/FreeOTPPlus). The intended usage is to install this app, export a backup on the original FreeOTP app, 
convert the backup file with this app, import the converted file to FreeOTP+, and delete this app.
Important: The backup export file is encrypted with the password set when FreeOTP is first installed. The password to be entered when converting a backup must be the one you've set on the *original* FreeOTP app.

Note that this app is build from FreeOTP version 2.0.2, if the backup format is changed in a newer version, it will break this app.

## How it works

The tokens' secrets are stored in Android Keystore (usually an HSM) which restricts access. However this restriction also applies to the app itself,
therefore, the app does not read the secrets from the Keystore when it needs to make a backup. Instead, whenever a new token is added, the
secret is encrypted with a password-derived key and stored in the app. Then the encrypted secret is simply written to the backup file whenever required.
Therefore, the simplest way to export secret without dealing with the Keystore is to pick up the secrets whenever they are decrypted from a backup.
This fork does exactly that: First select the backup file, enter the password as usual, then choose where to save the file with the tokens in URI form.

If you forgot your password (t would be technically possible to read the secrets from the key