"use client"

import React, {useContext} from "react";
import {FunctionComponentProps} from "@jansora/ui/esm/lib/declares";
import Link from "next/link";
import {GlobalStore} from "@/lib/store/global";


interface Props extends FunctionComponentProps {

}

const HeaderRight = ({children}: Props) => {

    const { categories} = useContext(GlobalStore);

    return (
        <>
            <Link href="/" className="flex items-center">
                <span className="hidden sm:inline-block my-auto select-none text-muted-foreground text-sm ">
                                 {/*心之所向，素履以往；生如逆旅，一苇以航*/}
                </span>
            </Link>

        </>
    )
}

export default HeaderRight;

