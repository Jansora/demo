'use client'

import React, {useState} from "react";
import GetDarkMode from "@jansora/ui/esm/components/enhanced/plugins/GetDarkMode";
import {
    AdminSubLayout,
    AdminSubLayoutAside,
    AdminSubLayoutContent
} from "@jansora/ui/esm/components/enhanced/layout/AdminSubLayout";
import {SidebarNav} from "@jansora/ui/esm/components/enhanced/layout/SidebarNav";
import PageLayout from "@jansora/ui/esm/components/enhanced/layout/PageLayout";
import PaddedPageLayout from "@jansora/ui/esm/components/enhanced/layout/PaddedPageLayout";


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
        <PageLayout>

                    {children}

        </PageLayout>
    )
}
