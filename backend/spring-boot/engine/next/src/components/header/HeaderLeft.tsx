"use client"

import React from "react";
import {FunctionComponentProps} from "@jansora/ui/esm/lib/declares";
import Link from "next/link";
import {Waypoints} from "lucide-react";
import {Menubar} from "@jansora/ui/esm/components/ui/menubar";
import {NavLink} from "@jansora/ui/esm/components/enhanced/next/NavLink";

import {Separator} from "@jansora/ui/esm/components/ui/separator";
import {usePathname} from "next/navigation";
import {cn} from "@jansora/ui/esm/lib/utils";

interface Props extends FunctionComponentProps {

}

const HeaderLeft = ({children}: Props) => {


    const pathname = usePathname();

    console.log("pathname", pathname, pathname.startsWith("/demo") || pathname === "/demo")
    return (
        <>
            <Link href="/" className="flex items-center">

                <Waypoints className="mr-2 h-6 w-6 " />
                {/*<Command className="mr-2 h-8 w-8" />*/}
                <span className="hidden sm:inline-block my-auto select-none ">
                                 公式引擎
                                </span>
                <span className="inline-block sm:hidden  my-auto select-none ">
                                 公式引擎
                 </span>
            </Link>

            <Separator orientation="vertical" className="mx-3 h-5 "/>

            <NavLink href={`/`} className={cn("text-sm mr-3")} active={pathname.startsWith("/") || pathname === "/"}  > Hr 薪酬计算演示 </NavLink>


            <Menubar className="rounded-none border-b border-none lg:px-0">

            </Menubar>
            {children}
        </>
    )
}

export default HeaderLeft;

