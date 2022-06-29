
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RequestManager from "./components/listers/RequestCards"

import NotificationManager from "./components/listers/NotificationCards"

import AcctInfoManager from "./components/listers/AcctInfoCards"

import TransferManager from "./components/listers/TransferCards"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/requests',
                name: 'RequestManager',
                component: RequestManager
            },

            {
                path: '/notifications',
                name: 'NotificationManager',
                component: NotificationManager
            },

            {
                path: '/acctInfos',
                name: 'AcctInfoManager',
                component: AcctInfoManager
            },

            {
                path: '/transfers',
                name: 'TransferManager',
                component: TransferManager
            },



    ]
})
