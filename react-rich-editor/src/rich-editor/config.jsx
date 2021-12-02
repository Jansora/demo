//引入插件 begin
import Redo from '@aomao/plugin-redo';
import Undo from '@aomao/plugin-undo';
import Bold from '@aomao/plugin-bold';
import Code from '@aomao/plugin-code';
import Backcolor from '@aomao/plugin-backcolor';
import Fontcolor from '@aomao/plugin-fontcolor';
import Fontsize from '@aomao/plugin-fontsize';
import Italic from '@aomao/plugin-italic';
import Underline from '@aomao/plugin-underline';
import Hr, {HrComponent} from '@aomao/plugin-hr';
import Tasklist, {CheckboxComponent} from '@aomao/plugin-tasklist';
import Orderedlist from '@aomao/plugin-orderedlist';
import Unorderedlist from '@aomao/plugin-unorderedlist';
import Indent from '@aomao/plugin-indent';
import Heading from '@aomao/plugin-heading';
import Strikethrough from '@aomao/plugin-strikethrough';
import Sub from '@aomao/plugin-sub';
import Sup from '@aomao/plugin-sup';
import Alignment from '@aomao/plugin-alignment';
import Mark from '@aomao/plugin-mark';
import Quote from '@aomao/plugin-quote';
import PaintFormat from '@aomao/plugin-paintformat';
import RemoveFormat from '@aomao/plugin-removeformat';
import SelectAll from '@aomao/plugin-selectall';
import Link from '@aomao/plugin-link';
import Codeblock, {CodeBlockComponent} from '@aomao/plugin-codeblock';
import Image, {ImageComponent, ImageUploader} from '@aomao/plugin-image';
import Table, {TableComponent} from '@aomao/plugin-table';
import MarkRange from '@aomao/plugin-mark-range';
import File, {FileComponent, FileUploader} from '@aomao/plugin-file';
import Video, {VideoComponent, VideoUploader} from '@aomao/plugin-video';
import Math, {MathComponent} from '@aomao/plugin-math';
import Fontfamily from '@aomao/plugin-fontfamily';
import Status, {StatusComponent} from '@aomao/plugin-status';
import LineHeight from '@aomao/plugin-line-height';
import Mention, {MentionComponent} from '@aomao/plugin-mention';
import {fontFamilyDefaultData, ToolbarComponent, ToolbarPlugin,} from '@aomao/toolbar';
import 'antd/es/empty/style';

const DOMAIN = "/api"

export const plugins = [
    Redo,
    Undo,
    Bold,
    Code,
    Backcolor,
    Fontcolor,
    Fontsize,
    Italic,
    Underline,
    Hr,
    Tasklist,
    Orderedlist,
    Unorderedlist,
    Indent,
    Heading,
    Strikethrough,
    Sub,
    Sup,
    Alignment,
    Mark,
    Quote,
    PaintFormat,
    RemoveFormat,
    SelectAll,
    Link,
    Codeblock,
    Image,
    ImageUploader,
    Table,
    MarkRange,
    File,
    FileUploader,
    Video,
    VideoUploader,
    Math,
    ToolbarPlugin,
    Fontfamily,
    Status,
    LineHeight,
    Mention,
    // Test,
    //Mind
];

export const cards = [
    HrComponent,
    CheckboxComponent,
    CodeBlockComponent,
    ImageComponent,
    TableComponent,
    FileComponent,
    VideoComponent,
    MathComponent,
    ToolbarComponent,
    StatusComponent,
    MentionComponent,
    // TestComponent,
    //MindComponent
];

export const pluginConfig = {
    [MarkRange.pluginName]: {
        //标记类型集合
        keys: ['comment'],
    },
    [Italic.pluginName]: {
        // 默认为 _ 下划线，这里修改为单个 * 号
        markdown: '*',
    },
    [Image.pluginName]: {
        onBeforeRender: (status, url) => {
            if (!url) return url;
            return url + `?token=12323`;
        },
    },
    [ImageUploader.pluginName]: {
        file: {
            action: `${DOMAIN}/utils/upload`,
        },
        parse: response => {
            return {
                result: response.status,
                data: response.data
            }
        },
    },
    [FileUploader.pluginName]: {
        action: `${DOMAIN}/utils/upload`,
        parse: response => {
            return {
                result: response.status,
                data: {
                    url: response.data,
                }
            }
        },
    },
    [VideoUploader.pluginName]: {
        file: {
            action: `${DOMAIN}/utils/upload`,
        },
        parse: response => {
            return {
                result: response.status,
                data: response.data
            }
        },
        limitSize: 1024 * 1024 * 50,
    },
    [Video.pluginName]: {
        onBeforeRender: (status, url) => {
            return url + `?token=12323`;
        },
    },
    [Math.pluginName]: {
        action: `https://g.yanmao.cc/latex`,
        parse: (res) => {
            if (res.success) return { result: true, data: res.svg };
            return { result: false };
        },
    },
    [Fontsize.pluginName]: {
        //配置粘贴后需要过滤的字体大小
        filter: (fontSize) => {
            return (
                [
                    '12px',
                    '13px',
                    '14px',
                    '15px',
                    '16px',
                    '19px',
                    '22px',
                    '24px',
                    '29px',
                    '32px',
                    '40px',
                    '48px',
                ].indexOf(fontSize) > -1
            );
        },
    },
    [Fontfamily.pluginName]: {
        //配置粘贴后需要过滤的字体
        filter: (fontfamily) => {
            const item = fontFamilyDefaultData.find((item) =>
                fontfamily
                    .split(',')
                    .some(
                        (name) =>
                            item.value
                                .toLowerCase()
                                .indexOf(name.replace(/"/, '').toLowerCase()) >
                            -1,
                    ),
            );
            return item ? item.value : false;
        },
    },
    [LineHeight.pluginName]: {
        //配置粘贴后需要过滤的行高
        filter: (lineHeight) => {
            if (lineHeight === '14px') return '1';
            if (lineHeight === '16px') return '1.15';
            if (lineHeight === '21px') return '1.5';
            if (lineHeight === '28px') return '2';
            if (lineHeight === '35px') return '2.5';
            if (lineHeight === '42px') return '3';
            // 不满足条件就移除掉
            return (
                ['1', '1.15', '1.5', '2', '2.5', '3'].indexOf(lineHeight) > -1
            );
        },
    },
};


