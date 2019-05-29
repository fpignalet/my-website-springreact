module.exports = {
    "id": 1,

    "content1": "CONTENT 1",
    "content2": "CONTENT 2",
    "content3": {
      "id":1,
      "content1": [ "CONTENT 311", "CONTENT 312"  ],
      "content2": [ "CONTENT 321", "CONTENT 322"  ]
    },

    "testtext": [
        "CLICK ME!",
        "Do something",
        "Now displaying the following list\n"
    ],
    "testdata": [
        "JSX Test table Item1 ",
        "JSX Test table Item2 "
    ],

    "collapsable-text": {
        "text": "EXAMPLE COLLAPSABLE TEXT EXAMPLE COLLAPSABLE TEXT \n",
        "subcontent":
            {
                "text": "EXAMPLE COLLAPSABLE SUBTEXT EXAMPLE COLLAPSABLE SUBTEXT \n",
                "subcontent":
                    {
                        "text": "EXAMPLE COLLAPSABLE SUBSUBTEXT EXAMPLE COLLAPSABLE SUBSUBTEXT \n",
                        "subcontent": null
                    }
            }
    },

    "link-text1": "LINK-1-= THIS IS TEXT 1",
    "link-text2": "LINK-2-= THIS IS TEXT 2"
}