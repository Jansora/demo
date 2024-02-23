// import {fetchServerCurrentUser} from "@/lib/server/fetch-server";
import {Request} from "next/dist/compiled/@edge-runtime/primitives";
import {NextResponse} from "next/server";
import {fetchServer} from "@/lib/fetch/server/fetch-server";


export async function GET(_request: Request, context): Promise<NextResponse> {

    const { search } = new URL(_request.url)

    const { paths } = context.params

    // console.log("_request", search)
    // _request.json(
    const {responseData} =  await fetchServer(`/${paths.join("/")}${search}`);

    return responseData;

}

export async function POST(_request: Request, context) {
    const { paths } = context.params


    const {responseData} = await fetchServer(`/${paths.join("/")}`, {
        method: 'POST',
        body: JSON.stringify(await _request.json())
    })

    return responseData;
}

export async function PUT(_request: Request, context) {
    const { paths } = context.params


    const {responseData} = await fetchServer(`/${paths.join("/")}`, {
        method: 'PUT',
        body: JSON.stringify(await _request.json())
    })

    return responseData;
}


export async function DELETE(_request: Request, context) {


    const { paths } = context.params

    const {responseData} =  await fetchServer(`/${paths.join("/")}`, {
        method: 'DELETE',
    });
    return responseData;

}