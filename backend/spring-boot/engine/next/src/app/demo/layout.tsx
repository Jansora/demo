'use client'

import React, {useState} from "react";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";
import {
    AdminSubLayout,
    AdminSubLayoutAside,
    AdminSubLayoutContent
} from "@jansora/ui/esm/components/enhanced/layout/AdminSubLayout";
import {SidebarNav} from "@jansora/ui/esm/components/enhanced/layout/SidebarNav";


const sidebarNavItems = [
    {
        title: "规则配置",
        href: "/drools/config",
    },
    {
        title: "规则运行",
        href: "/drools/run",
    },
]

export default function Layout({children}) {
    const [code, setCode] = useState("")

    const dark = GetDarkMode();
    // const books = initBooks(config.categories[0])
    return (
        <>
            <AdminSubLayout description="规则热加载和实例化运行" title="Drools 后台管理" className="sm:w-full m-0 ">

                <AdminSubLayoutAside>
                    <SidebarNav items={sidebarNavItems} />
                </AdminSubLayoutAside>
                <AdminSubLayoutContent >
                    {children}
                </AdminSubLayoutContent>
            </AdminSubLayout>
        </>
    )
}
