"use client"

import React, {useContext} from "react";
import {notFound, useParams} from "next/navigation";
import {GlobalStore} from "@/lib/store/global";


export default function Page() {

    const { category } = useParams();

    const { categories} = useContext(GlobalStore);

    const found = categories.filter(_category => _category.key === category);
    if (found.length == 0) {
        notFound()
    }

    return (
        <>


        </>
    )
}
