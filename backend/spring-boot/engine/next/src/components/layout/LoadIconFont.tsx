import React from "react";

export default async function LoadIconFont() {


    const iconfontUrl = '//at.alicdn.com/t/c/font_4157193_uci343sx5m.js'

    return (
        <script src={iconfontUrl}/>
    )
}

