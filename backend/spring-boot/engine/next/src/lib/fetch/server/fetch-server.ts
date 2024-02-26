import {cookies} from 'next/headers'

import {ResultDto} from "@/lib/fetch";
import {NextResponse} from "next/server";

export function ServerHeader(): any  {

    // console.log(cookies())
    return {
        'Content-Type': 'application/json',
        'Cookie': cookies()
    }
}

export async function fetchServer(url, options?: Object, formBody?: boolean) {


    const response = await fetch(process.env.API_SERVER_URL + url, !formBody ? {
        headers: ServerHeader(),
        ...options ,
    }: (options || {}))

    let data: ResultDto<any>;
    if (response.ok) {
        data = await response.json()
    }
    else {
        data = JSON.parse(await response.text())
    }

    const responseData= NextResponse.json(data, {
        status: response.status,
        // @ts-ignore
        headers: (response.headers && response.headers.getSetCookie()) ? { 'Set-Cookie': response.headers.getSetCookie() } : {},
    })

    return {response, data, responseData};
}

