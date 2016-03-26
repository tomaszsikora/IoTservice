package com.sikora.tomasz.iotservice.e2e

import geb.Page

/**
 * Created by Tomasz Sikora for personal use.
 */
class MainPage extends Page{
    static url = '/'
    static at = {title.startsWith("IoT Service")}
}
