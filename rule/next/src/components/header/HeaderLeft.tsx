"use client"

import React, {useContext} from "react";
import {FunctionComponentProps} from "@jansora/ui/esm/lib/declares";
import Link from "next/link";
import {BookOpenText} from "lucide-react";
import {Menubar} from "@jansora/ui/esm/components/ui/menubar";
import {GlobalStore} from "@/lib/store/global";
import {NavLink} from "@jansora/ui/esm/components/enhanced/next/NavLink";

import {Separator} from "@jansora/ui/esm/components/ui/separator";

interface Props extends FunctionComponentProps {

}

const HeaderLeft = ({children}: Props) => {

    const { categories} = useContext(GlobalStore);

    return (
        <>
            <Link href="/" className="flex items-center">
                <BookOpenText className="mr-2 h-6 w-6 " />
                {/*<Command className="mr-2 h-8 w-8" />*/}
                <span className="hidden sm:inline-block my-auto select-none ">
                                 ElasticBook
                                </span>
                <span className="inline-block sm:hidden  my-auto select-none ">
                                 ElasticBook
                 </span>
            </Link>

            <Separator orientation="vertical" className="mx-3 h-5 "/>
            {
                categories.map(category => {
                    return <NavLink key={category.key} href={`/category/${category.key}`} >
                            {category.value}
                        </NavLink>
                })
            }
            <Menubar className="rounded-none border-b border-none lg:px-0">



                {/*{*/}
                {/*    categories.map(category => {*/}
                {/*        return <MenubarMenu key={category.key}>*/}
                {/*            <MenubarTrigger className="font-bold w-20">*/}
                {/*                {category.value}*/}
                {/*            </MenubarTrigger>*/}
                {/*            <MenubarContent className="overscroll-y-auto" style={{overflowY: 'auto'}}>*/}
                {/*                {*/}
                {/*                    category.books.map(book => {*/}
                {/*                        return <MenubarItem key={book.key} onClick={event => {*/}
                {/*                                    event.preventDefault();*/}
                {/*                                }}>*/}
                {/*                            <Link href={`/category/${category.key}/book/${book.key}`}>*/}
                {/*                                {book.value}*/}
                {/*                            </Link>*/}
                {/*                        </MenubarItem>*/}
                {/*                    })*/}
                {/*                }*/}
                {/*            </MenubarContent>*/}
                {/*        </MenubarMenu>*/}
                {/*    })*/}
                {/*}*/}

            </Menubar>
            {children}
        </>
    )
}

export default HeaderLeft;

