"use client"

import React from "react";
import {BookData} from "@/lib/declares/config";
import {ResizableHandle, ResizablePanel, ResizablePanelGroup,} from "@jansora/ui/esm/components/ui/resizable"
import Tree, {TreeProps} from "@jansora/ui/esm/components/enhanced/ui/Tree";
import {hash} from "@/lib/util/utils";
import Link from "next/link";
import {useParams} from "next/navigation";
import {FunctionComponentProps} from "@jansora/ui/esm/lib/declares";
import {cn} from "@jansora/ui/esm/lib/utils";

interface Props extends FunctionComponentProps{
    data: BookData[]
}
export default function RenderMenu({data, children, className}: Props) {

    const { category, book } = useParams();

    const toTreeMenu = (menus: BookData[]): TreeProps[] => {

        return menus.map(menu => {

            const key =  hash(menu.path)
            return {
                title: menu.name,
                value: key,
                className: "",
                href: `/category/${category}/book/${book}/doc/${key}`,
                element: <Link href={`/category/${category}/book/${book}/doc/${key}`} > {menu.name} </Link>,
                children: menu.children ? toTreeMenu(menu.children) : []
            }
        });

    }

    return (
        <>
            <ResizablePanelGroup
                direction="horizontal"
                className={cn("rounded-lg border w-full py-4", className)}
            >
                <ResizablePanel defaultSize={33}>
                    <div  className={"overflow-y-auto px-4 "}  style={{height: "calc(100vh - 100px)"}}>
                        <Tree nodes={toTreeMenu(data)}/>
                    </div>

                </ResizablePanel>
                <ResizableHandle />
                <ResizablePanel defaultSize={66}>
                    {/*<div  className={" px-4"} >*/}

                    {/*</div>*/}
                    <div  className={"overflow-y-auto px-8 "}  style={{height: "calc(100vh - 100px)"}}>
                        {children}
                    </div>
                </ResizablePanel>
            </ResizablePanelGroup>

        </>
    )
}



