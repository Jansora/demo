//
// export async function fetchClientSearch(moduleUri: string, options: SearchRequest): Promise<{
//     data: ResultDto<PageResponse<SearchResponse>>;
//     response: Response;
//     responseData: NextResponse<SearchResponse>
// }> {
//
//
//
//     const keywords = options.keywords ? options.keywords : ""
//     const name = options.keywords ? options.keywords : ""
//
//     const classify = options.classify ? options.classify : ""
//     const tag = options.tag ? options.tag : ""
//     const pageSize = options.pageSize ? options.pageSize : "18"
//     const pageNum = options.pageNum ? options.pageNum : "1"
//     const sort = options.sort ? options.sort : "DESC"
//     const orderBy = options.orderBy ? options.orderBy : "updated_at"
//
//
//     const args = {...options, name, keywords, classify, tag, pageSize, pageNum, orderBy, sort}
//
//     const {data} = await fetchClient(`/${moduleUri}/search?${stringify(args)}`, {
//         method: 'GET',
//     })
//
//     //@ts-ignore
//     return await fetchServer(
//         `/${moduleUri}/search?${stringify(args)}`, {
//             method: 'GET',
//         })
// }


// export async function fetchOne(moduleUri: string, id: string): Promise<{
//     data: ResultDto<any>;
//     response: Response;
//     responseData: NextResponse<any>
// }> {
//     return await fetchServer(
//         `/${moduleUri}/${id}`, {
//             method: 'GET',
//         })
// }

import {fetchClient} from "@/lib/fetch/client/fetch-client";
import {toast} from "@jansora/ui/esm/components/ui/use-toast";

export async function fetchClientModuleDeleteOne(module: string, id: string, successCallback: Function, errorCallback?: Function) {


    const result = await fetchClient(`/modules/${module}/${id}`, {
        method: 'DELETE'
    })


    if (result.data.status) {
        toast({
            description: "删除成功",
        })
        successCallback && successCallback(result.data.data)
    }
    else {
        errorCallback && errorCallback(result.data)
    }


}


