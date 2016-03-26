package com.sikora.tomasz.iotservice.e2e

import geb.Page

/**
 * Created by Tomasz Sikora for personal use.
 */
class DevicePage extends Page{
    static url = '/device'
    static at = {title.startsWith("Registered devices")}
}
