// pages/api/user.js
import {Stats} from "fs";
import {NextResponse} from "next/server";

import {Book} from "@/lib/declares/config";
import {loadBook} from "@/lib/config/initConfig";

import {ReadableOptions} from "stream";
import {STATIC} from "@/lib/util/utils";

const fs = require('fs');
const path = require('path');


function streamFile(path: string, options?: ReadableOptions): ReadableStream<Uint8Array> {
    const downloadStream = fs.createReadStream(path, options);

    return new ReadableStream({
        start(controller) {
            downloadStream.on("data", (chunk: Buffer) => controller.enqueue(new Uint8Array(chunk)));
            downloadStream.on("end", () => controller.close());
            downloadStream.on("error", (error: NodeJS.ErrnoException) => controller.error(error));
        },
        cancel() {
            downloadStream.destroy();
        },
    });
}

export async function GET(req: Request, context): Promise<NextResponse> {

    const {id} = context.params
    const {category, book, doc, resources} =  context.params;
    const bookCache: Book = loadBook(book);

    const ext = path.extname(resources[resources.length - 1]).toLowerCase();

    if (!bookCache) {
        return  new NextResponse(null, {
            status: 404,
        });
    }



    const file = bookCache.path + `${doc === STATIC ? "" : "/" + doc}/${resources.join("/")}`;
        // "/home/manjaro/Downloads/manjaro-kde-22.1.3-230529-linux61.iso"; // req.nextUrl.searchParams.get("file");

    const stats: Stats = await fs.promises.stat(file);
    const data: ReadableStream<Uint8Array> = streamFile(file);   //Stream the file with a 1kb chunk

    let contentType = `application/${ext.startsWith(".") ? ext.substring(1, ext.length) : ext}`

    if (ext === ".html") {
        contentType = "text/html; charset=utf-8"
    }

    const res = new NextResponse(data, {
        status: 200,
        headers: new Headers({
            // "content-disposition": `attachment; filename=${path.basename(file)}`,
            "content-type": contentType,
            "content-length": stats.size + "",
        }),
    });

    return res;
}
