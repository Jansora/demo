import React from "react";
import {BookData} from "@/lib/declares/config";
import path from "path";


export default function RenderHtml({data, params, resourcePath}: {data: BookData, params: any, resourcePath: string}) {

    // const { name, path } = data;
    const fileName = path.basename(data.path);
    const { category, book, doc } = params;



    return  <div className="h-full w-full">
        <iframe
            allowFullScreen={true}
            src={resourcePath} width="100%" height={`100%`}></iframe>
    </div>

}



